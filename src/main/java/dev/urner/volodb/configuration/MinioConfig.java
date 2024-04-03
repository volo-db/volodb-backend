package dev.urner.volodb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {

  @Value("${volodb.minio.url}")
  private String url;

  @Value("${volodb.minio.access-key}")
  private String accessKey;

  @Value("${volodb.minio.secret-key}")
  private String secretKey;

  @Bean
  public MinioClient minioClient() {
    return MinioClient.builder()
        .endpoint(url)
        .credentials(accessKey, secretKey)
        .build();
  }

}
