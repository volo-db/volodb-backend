package dev.urner.volodb.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;

@Service
@RequiredArgsConstructor
public class DocumentService {

  public byte[] generatePdfFromHtml(String htmlContent) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
      Page page = browser.newPage();

      // Setze den HTML-Inhalt der Seite
      page.setContent(htmlContent);

      // Erzeuge die PDF und speichere sie in einem Byte-Array
      byte[] pdfBytes = page.pdf(new Page.PdfOptions().setFormat("A4"));

      // Schließe den Browser
      browser.close();

      return pdfBytes;
    } catch (Exception e) {
      System.out.println("HILFE: " + e.getMessage().toString());
      return null;
    }
  }

}
