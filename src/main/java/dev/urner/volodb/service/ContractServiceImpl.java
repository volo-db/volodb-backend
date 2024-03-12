package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ContractDAO;
import dev.urner.volodb.entity.Contract;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

  private final ContractDAO contractDAO;

  @Override
  public List<Contract> findAll() {
    return contractDAO.findAll();
  }

  @Override
  public Contract findById(int contractId) {
    return contractDAO.findById(contractId);
  }

  @Override
  public List<Contract> findByVolunteerId(int volunteerId) {
    return contractDAO.findByVolunteerId(volunteerId);
  }

}
