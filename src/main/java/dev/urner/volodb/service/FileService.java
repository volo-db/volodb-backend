package dev.urner.volodb.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
  private final MinioClient minioClient;

  public InputStream getFile(String bucket, String object) {
    try {
      InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(object).build());
      return stream;
    } catch (Exception e) {
      throw new RuntimeException("File '" + object + "' in bucket '" + bucket + "' not found.");
    }
  }
}
