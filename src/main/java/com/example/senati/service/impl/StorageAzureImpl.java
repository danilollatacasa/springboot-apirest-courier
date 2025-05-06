package com.example.senati.service.impl;

import com.azure.storage.blob.models.BlobHttpHeaders;
import com.example.senati.service.IStorageAzure;
import com.azure.storage.blob.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class StorageAzureImpl implements IStorageAzure {

    private final BlobContainerClient fotosContainerClient;
    private final BlobContainerClient codigosBarraContainerClient;

    // Constructor con inyección de valores desde application.properties
    public StorageAzureImpl(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container.fotos}") String fotosContainerName,
            @Value("${azure.storage.container.codigos-barra}") String codigosBarraContainerName) {

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Crear clientes para los dos contenedores
        this.fotosContainerClient = serviceClient.getBlobContainerClient(fotosContainerName);
        this.codigosBarraContainerClient = serviceClient.getBlobContainerClient(codigosBarraContainerName);
    }

    @Override
    public String uploadFile(InputStream fileStream, String fileName, String contentType, boolean isCodigoBarra) {
        try {
            // Usar el contenedor correcto según el tipo
            BlobContainerClient selectedContainer = isCodigoBarra ? codigosBarraContainerClient : fotosContainerClient;

            BlobClient blobClient = selectedContainer.getBlobClient(fileName);
            BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(contentType);
            blobClient.upload(fileStream, fileStream.available(), true);
            blobClient.setHttpHeaders(headers);
            return blobClient.getBlobUrl();
        } catch (IOException e) {
            throw new RuntimeException("Error al subir archivo a Azure", e);
        }
    }

}


