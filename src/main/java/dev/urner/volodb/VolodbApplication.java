package dev.urner.volodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class VolodbApplication {

  public static void main(String[] args) {
    SpringApplication.run(VolodbApplication.class, args);

  }

}

@Component
class MyApplicationRunner implements ApplicationRunner {

  @Value("${volodb.minio.test}")
  private String testVal;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("✨✨✨✨✨: " + testVal);
  }
}