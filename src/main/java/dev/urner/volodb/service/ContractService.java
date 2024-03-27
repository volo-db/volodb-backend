package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ContractDAO;
import dev.urner.volodb.entity.Contract;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractService {

  private final ContractDAO contractDAO;

  public List<Contract> findAll() {
    return contractDAO.findAll();
  }

  public Contract findById(int contractId) {
    return contractDAO.findById(contractId);
  }

  public List<Contract> findByVolunteerId(int volunteerId) {
    return contractDAO.findAllByVolunteerId(volunteerId);
  }

}
