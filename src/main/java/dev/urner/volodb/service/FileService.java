package dev.urner.volodb.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
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

  public void saveFile(MultipartFile file, String bucket, String object) {
    try {
      // If Bucket don't exist create it

      if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
        minioClient.makeBucket(
            MakeBucketArgs.builder()
                .bucket(bucket)
                .build());
      }

      minioClient.putObject(PutObjectArgs.builder()
          .bucket(bucket)
          .object(object)
          .stream(file.getInputStream(), file.getSize(), -1)
          .build());

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public void deleteFile(String bucket, String object) {
    try {
      minioClient.removeObject(RemoveObjectArgs.builder()
          .bucket(bucket)
          .object(object)
          .build());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
