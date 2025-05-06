package com.example.senati.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IStorageAzure {
    String uploadFile(InputStream fileStream, String fileName, String contentType, boolean isCodigoBarra);
}
