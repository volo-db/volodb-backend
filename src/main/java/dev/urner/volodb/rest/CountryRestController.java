package dev.urner.volodb.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.Country;
import dev.urner.volodb.exception.CountryNotFoundException;
import dev.urner.volodb.service.CountryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/countrys")
@RequiredArgsConstructor
public class CountryRestController {

  private final CountryService countryService;

  @GetMapping()
  public List<Country> getCountrys() {
    return countryService.findAll();
  }

  @ExceptionHandler
  public ResponseEntity<VolodbErrorResponse> handleException(CountryNotFoundException exc) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    VolodbErrorResponse error = new VolodbErrorResponse(
        httpStatus.value(),
        exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, httpStatus);
  }

}
