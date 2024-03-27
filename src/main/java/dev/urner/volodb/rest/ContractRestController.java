package dev.urner.volodb.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.urner.volodb.entity.Contract;
import dev.urner.volodb.service.ContractService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractRestController {

  private final ContractService contractService;

  @GetMapping
  public List<Contract> findAll() {
    return contractService.findAll();
  }

  @GetMapping("{volunteerId}")
  public List<Contract> getMethodName(@PathVariable int volunteerId) {
    return contractService.findByVolunteerId(volunteerId);
  }

}
