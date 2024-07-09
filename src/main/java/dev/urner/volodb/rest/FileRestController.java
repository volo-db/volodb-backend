package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileRestController {

  private final FileService fileService;

  @GetMapping("/{bucket}/**")
  public void getTestMethod(@PathVariable("bucket") String bucket, HttpServletRequest request,
      HttpServletResponse response, @RequestParam(name = "download", defaultValue = "true") Boolean download)
      throws IOException {

    // Extract object-string from URL
    String object = request.getRequestURI().split(bucket)[1];

    InputStream inputStream = fileService.getFile(bucket, object);

    // force the browser to download the file
    if (download)
      response.addHeader("Content-disposition", "attachment;filename=" + object);

    response.setContentType(URLConnection.guessContentTypeFromName(object));

    IOUtils.copy(inputStream, response.getOutputStream());
    response.flushBuffer();
  }

  @ExceptionHandler
  public ResponseEntity<VolodbErrorResponse> handleException(RuntimeException exc) {

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    VolodbErrorResponse error = new VolodbErrorResponse(
        httpStatus.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

}
