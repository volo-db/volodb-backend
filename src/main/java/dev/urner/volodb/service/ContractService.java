package dev.urner.volodb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ContractDAO;
import dev.urner.volodb.dao.ContractModificationDAO;
import dev.urner.volodb.dao.ContractSalaryDAO;
import dev.urner.volodb.entity.Address;
import dev.urner.volodb.entity.Contract;
import dev.urner.volodb.entity.ContractModification;
import dev.urner.volodb.entity.Country;
import dev.urner.volodb.entity.Enums.AddressStatus;
import dev.urner.volodb.exception.ContractModificationNotFoundException;
import dev.urner.volodb.exception.ContractNotFoundException;
import dev.urner.volodb.exception.VolunteerInvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ContractService {

  private final ContractDAO contractDAO;
  private final ContractModificationDAO contractModificationDAO;
  private final ContractSalaryDAO contractSalaryDAO;

  public List<Contract> findAll() {
    return contractDAO.findAll();
  }

  public Contract findById(int contractId) {
    return modifyContract(contractDAO.findById(contractId));
  }

  public List<Contract> findAllByVolunteerId(int volunteerId) {
    List<Contract> dbContracts = contractDAO.findAllByVolunteerId(volunteerId);
    for (Contract dbContract : dbContracts) {
      dbContract = modifyContract(dbContract);
    }
    return dbContracts;
  }

  public Contract findRelevantContractByVolunteerId(int volunteerId) {
    Contract relevantContract = null;
    List<Contract> dbContracts = findAllByVolunteerId(volunteerId);

    // If there's just one contract -> this IS the relevant contract
    if (dbContracts.size() == 1)
      return dbContracts.get(0);

    dbContracts.sort((contract1, contract2) -> contract1.getStart().compareTo(contract2.getStart()));

    for (Contract contract : dbContracts) {
      // If today is between start and end of this contract -> this IS the relevant
      // contract
      if ((LocalDate.now().isEqual(contract.getStart()) || LocalDate.now().isAfter(contract.getStart()))
          && (LocalDate.now().isBefore(contract.getEnd()) || LocalDate.now().isEqual(contract.getEnd()))) {
        relevantContract = contract;
        break;
      }

      // If today is after the end of the contract this COULD be the relevant contract
      // (assuming there's no contract with a closer end-date)
      if (LocalDate.now().isAfter(contract.getEnd())) {
        relevantContract = contract;
      }

      // If today is before the start of the contract this COULD be the relevant
      // contract (assuming there's no contract with a closer start-date )
      if (LocalDate.now().isBefore(contract.getStart()) && relevantContract.equals(null)
          || LocalDate.now().isBefore(contract.getStart()) && LocalDate.now().isAfter(relevantContract.getEnd())) {
        relevantContract = contract;
      }

    }

    return relevantContract;
  }

  public List<ContractModification> findModifications(int volunteerId, int contractId) {
    Contract dbContract = contractDAO.findById(contractId);

    if (dbContract.getVolunteer().getId() != volunteerId)
      throw new ContractNotFoundException(
          "Volunteer has no contract with the given id of " + contractId);

    return contractModificationDAO.findAllByContractId(dbContract.getId());

  }

  private Contract modifyContract(Contract contract) {

    List<ContractModification> modifications = contractModificationDAO.findAllByContractId(contract.getId());

    // sorting the List by timestamp
    modifications.sort((mod1, mod2) -> mod1.getTimestamp().compareTo(mod2.getTimestamp()));

    for (ContractModification modification : modifications) {

      // if modification is not approved (requested or rejected) go to next
      if (modification.getStatus().getId() != 2)
        continue;

      switch (modification.getType().getId()) {
        case 1: // "Änderung der Bezüge"
          contract.setSalary(contractSalaryDAO.findById(Integer.parseInt(modification.getValue())));
          break;

        case 2: // "Verlängerung"
        case 3: // "Verkürzung"
        case 4: // "Aufhebung"
        case 5: // "Kündigung"
          contract.setEnd(LocalDate.parse(modification.getValue()));
          break;

        default:
          throw new ContractModificationNotFoundException(
              "There's no handler for Contract-Modification-Type with id: " + modification.getType().getId());
      }
    }
    return contract;
  }

  public Contract setSickDays(int volunteerId, int contractId, Map<String, Object> fields) {
    Contract dbContract = contractDAO.findById(contractId);

    if (dbContract.equals(null) || dbContract.getVolunteer().getId() != volunteerId)
      throw new VolunteerInvalidFormatException("No Contract with id " + contractId + " for Volunteer.");

    fields.forEach((key, value) -> {

      if (key.toLowerCase().equals("sickdays")) {
        if (!value.equals(null) && !value.equals("") && Integer.parseInt(value.toString()) >= 0)
          dbContract.setSickDays(Integer.parseInt(value.toString()));
      }

    });

    return contractDAO.save(dbContract);
  }

}
