package com.example.senati.service.impl;

import com.example.senati.model.entity.EstadoPaquete;
import com.example.senati.model.entity.Paquete;
import com.example.senati.model.repository.EstadoPaqueteRepository;
import com.example.senati.model.repository.PaqueteRepository;
import com.example.senati.service.IPaquete;
import com.example.senati.service.IStorageAzure;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PaqueteImpl implements IPaquete {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private IStorageAzure storageAzure;

    @Autowired
    private EstadoPaqueteRepository estadoPaqueteRepository;

    @Override
    public List<Paquete> getAll() {
        return paqueteRepository.findAll();
    }

    @Override
    public Paquete getById(Integer id) {
        return paqueteRepository.findById(id).orElse(null);
    }

    @Override
    public Paquete save(Paquete paquete) {
        try {
            // Verificar que la fecha de entrega no sea nula
            if (paquete.getFechaEntrega() == null) {
                throw new IllegalArgumentException("La fecha de entrega no puede ser nula");
            }

            // Verificar que la latitud y longitud no sean nulas ni vacías
            if (paquete.getLatitud() == null || paquete.getLatitud().isBlank() ||
                    paquete.getLongitud() == null || paquete.getLongitud().isBlank()) {
                throw new IllegalArgumentException("Latitud y longitud no pueden ser nulas o vacías");
            }

            // Buscar el estado "pendiente" (ignorando mayúsculas/minúsculas)
            EstadoPaquete estadoPendiente = estadoPaqueteRepository
                    .findByDescripcionIgnoreCase("pendiente")
                    .orElseThrow(() -> new RuntimeException("Estado 'pendiente' no encontrado"));

            // Asignar el estado al paquete antes de guardar
            paquete.setEstado(estadoPendiente);

            // Guardar el paquete sin código para obtener el ID generado
            Paquete paqueteGuardado = paqueteRepository.save(paquete);
            Integer id = paqueteGuardado.getId();

            // Formatear la fecha de entrega a "yyMMdd"
            String fechaEntregaStr = paquete.getFechaEntrega().format(DateTimeFormatter.ofPattern("yyMMdd"));

            // Generar el código de barras: GK-fecha-ID
            String idStr = String.format("%08d", id);
            String codigoBarra = "GK-" + fechaEntregaStr + "-" + idStr;

            // Generar imagen del código de barras
            BufferedImage imagen = generarCodigoBarras(codigoBarra);

            // Convertir la imagen a InputStream para subirla a Azure
            InputStream imagenStream = convertirImagenAInputStream(imagen);
            String url = storageAzure.uploadFile(imagenStream, codigoBarra + ".png", "image/png", true);

            // Actualizar el paquete con el código de barras y la URL
            paqueteGuardado.setCodigoBarra(codigoBarra);
            paqueteGuardado.setCodigoBarraUrl(url);

            // Guardar nuevamente con el código de barras
            return paqueteRepository.save(paqueteGuardado);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el paquete y generar el código de barras", e);
        }
    }

    @Override
    public Paquete update(Integer id, Paquete paqueteActualizado) {
        return paqueteRepository.findById(id).map(paquete -> {
            // Validar latitud y longitud
            if (paqueteActualizado.getLatitud() == null || paqueteActualizado.getLatitud().isBlank() ||
                    paqueteActualizado.getLongitud() == null || paqueteActualizado.getLongitud().isBlank()) {
                throw new IllegalArgumentException("Latitud y longitud no pueden ser nulas o vacías");
            }

            // Actualizar los campos del paquete
            paquete.setProducto(paqueteActualizado.getProducto());
            paquete.setRemitente(paqueteActualizado.getRemitente());
            paquete.setDestinatario(paqueteActualizado.getDestinatario());
            paquete.setFechaLlegada(paqueteActualizado.getFechaLlegada());
            paquete.setFechaEntrega(paqueteActualizado.getFechaEntrega());
            paquete.setNumeroContacto(paqueteActualizado.getNumeroContacto());
            paquete.setDireccion(paqueteActualizado.getDireccion());
            paquete.setLatitud(paqueteActualizado.getLatitud());
            paquete.setLongitud(paqueteActualizado.getLongitud());

            // Aquí se puede permitir la actualización del estado si se desea cambiar más adelante
            // Si no se desea actualizar el estado, puedes descomentar la siguiente línea:
            // paquete.setEstado(paqueteActualizado.getEstado());

            // Evitar que el estado cambie sin validación adicional si no lo deseas
            paquete.setDistrito(paqueteActualizado.getDistrito());

            return paqueteRepository.save(paquete);
        }).orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
    }

    @Override
    public void delete(Paquete paquete) {
        paqueteRepository.delete(paquete);
    }

    // Generador de imagen de código de barras
    private BufferedImage generarCodigoBarras(String texto) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(texto, BarcodeFormat.CODE_128, 400, 150);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    // Convertir BufferedImage a InputStream
    private InputStream convertirImagenAInputStream(BufferedImage imagen) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(imagen, "png", os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
