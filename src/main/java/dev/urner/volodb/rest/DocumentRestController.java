package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.service.DocumentService;
import dev.urner.volodb.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentRestController {

  private static final Logger logger = LoggerFactory.getLogger(DocumentRestController.class);

  private final FileService fileService;
  private final DocumentService documentService;

  @GetMapping("/{templateId}")
  public ResponseEntity<String> getTestMethod(@PathVariable("templateId") int templateId)
      throws IOException {

    String tempContente = "<!DOCTYPE html><head><meta charset='UTF-8'><title>Testdokument " + templateId
        + "</title></head><body><h1>Testdokument " + templateId
        + "</h1><p>Das ist ein Absatz!</p></body></html>";

    byte[] pdfBytes = documentService.generatePdfFromHtml(tempContente);

    // Encode the PDF in Base64
    String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

    // Create HTML with embedded PDF preview
    String previewHtml = "<html><head>"
        + "<style>"
        + ":root { box-sizing: border-box; }"
        + "body { margin: 0; }"
        + "embed { width: 100vw; height: 100vh; }"
        + "</style></head>"
        + "<body>"
        + "<embed src='data:application/pdf;base64," + base64Pdf + "'></embed>"
        + "</body></html>";

    // Return the HTML as a response
    return new ResponseEntity<>(previewHtml, HttpStatus.OK);

  }

  @PostMapping("")
  public ResponseEntity<String> createPdf(@RequestBody Map<String, Object> data)
      throws IOException {

    String htmlContent = "";

    for (Map.Entry<String, Object> entry : data.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
      if (entry.getKey().toLowerCase() == "html") {
        htmlContent = entry.getValue().toString();
        logger.info(htmlContent);
      }
    }

    byte[] pdfBytes = documentService.generatePdfFromHtml(htmlContent);

    // Encode the PDF in Base64
    String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

    // Create HTML with embedded PDF preview
    String previewHtml = "<html><head>"
        + "<style>"
        + ":root { box-sizing: border-box; }"
        + "body { margin: 0; }"
        + "embed { width: 100vw; height: 100vh; }"
        + "</style></head>"
        + "<body>"
        + "<embed src='data:application/pdf;base64," + base64Pdf + "'></embed>"
        + "</body></html>";

    // Return the HTML as a response
    return new ResponseEntity<>(previewHtml, HttpStatus.OK);

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
