package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileRestController {

  private final FileService fileService;

  @GetMapping("/{bucket}/**")
  public void getTestMethod(@PathVariable("bucket") String bucket, HttpServletRequest request,
      HttpServletResponse response) throws IOException {

    // Extract object-string from URL
    String object = request.getRequestURI().split(bucket)[1];

    InputStream inputStream = fileService.getFile(bucket, object);

    // response.addHeader("Content-disposition", "attachment;filename=" + object);
    // /* <- force the browser to download the file */
    response.setContentType(URLConnection.guessContentTypeFromName(object));

    IOUtils.copy(inputStream, response.getOutputStream());
    response.flushBuffer();
  }

  @ExceptionHandler
  public ResponseEntity<VolunteerErrorResponse> handleException(RuntimeException exc) {
    VolunteerErrorResponse error = new VolunteerErrorResponse();

    HttpStatus status = HttpStatus.BAD_REQUEST;

    error.setStatus(status.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, status);
  }

}
