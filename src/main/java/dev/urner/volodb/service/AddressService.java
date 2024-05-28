package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.AddressDAO;
import dev.urner.volodb.dao.CountryDAO;
import dev.urner.volodb.dao.VolunteerDAO;
import dev.urner.volodb.entity.Address;
import dev.urner.volodb.entity.Country;
import dev.urner.volodb.entity.Enums.AddressStatus;
import dev.urner.volodb.exception.AddressInvalidFormatException;
import dev.urner.volodb.exception.AddressStatusNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AddressService {
  private final AddressDAO addressDAO;
  private final VolunteerDAO volunteerDAO;
  private final CountryDAO countryDAO;

  public List<Address> findAllByVolunteerId(int volunteerId) {
    int personId = volunteerDAO.findById(volunteerId).getPerson().getId();
    return addressDAO.findAllByPersonId(personId);
  }

  public Address findById(int addressId) {
    return addressDAO.findById(addressId);
  }

  @Transactional
  public Address save(Address address) {
    return addressDAO.save(address);
  }

  // @Transactional
  // public Contact save(int volunteerId, Map<String, Object> fields) {
  // Contact newContact = new Contact();
  // newContact.setPersonId(volunteerDAO.findById(volunteerId).getPerson().getId());

  // fields.forEach((key, value) -> {
  // if (key.toLowerCase().equals("type")) {
  // ContactType dbContactType =
  // contactTypeDAO.findByName(value.toString().toLowerCase());
  // if (dbContactType == null) {
  // throw new ContactTypeNotFoundException("Contact with type: \"" +
  // value.toString() + "\" not found.");
  // }

  // newContact.setType(dbContactType);
  // }

  // if (key.toLowerCase().equals("value")) {
  // newContact.setValue(value.toString());
  // }
  // });

  // return contactDAO.save(newContact);
  // }

  @Transactional
  public Address save(int volunteerId, Map<String, Object> fields) {
    Address newAddress = new Address();
    newAddress.setPersonId(volunteerDAO.findById(volunteerId).getPerson().getId());

    Map<String, Boolean> flags = new HashMap<>();
    flags.put("status", false);
    flags.put("name", false);
    flags.put("country", false);
    flags.put("street", false);
    flags.put("postalcode", false);
    flags.put("city", false);

    fields.forEach((key, value) -> {
      String lowercaseKey = key.toLowerCase();
      if (flags.containsKey(lowercaseKey)) {
        flags.put(lowercaseKey, true);
      }
    });

    if (!(flags.get("status") && flags.get("name") && flags.get("country")
        && flags.get("street") && flags.get("postalcode") && flags.get("city"))) {
      throw new AddressInvalidFormatException("not all fields are set correctly!");
    }

    fields.forEach((key, value) -> {
      if (key.toLowerCase().equals("status")) {

        switch (value.toString().toLowerCase()) {
          case "active":
            newAddress.setStatus(AddressStatus.ACTIVE);
            break;

          case "inactive":
            newAddress.setStatus(AddressStatus.INACTIVE);
            break;

          default:
            throw new AddressStatusNotFoundException("AddressSatus \"" + value.toString() + "\" is not valid.");
        }

      }

      if (key.toLowerCase().equals("name")) {
        newAddress.setName(value.toString());
      }
      if (key.toLowerCase().equals("careof") && value != null) {
        newAddress.setCareof(value.toString());
      }
      if (key.toLowerCase().equals("country")) {
        Country dbCountry = countryDAO.findByName(value.toString());
        newAddress.setCountry(dbCountry);
      }
      if (key.toLowerCase().equals("state") && value != null) {
        newAddress.setState(value.toString());
      }
      if (key.toLowerCase().equals("street")) {
        newAddress.setStreet(value.toString());
      }
      if (key.toLowerCase().equals("postalcode")) {
        newAddress.setPostalcode(value.toString());
      }
      if (key.toLowerCase().equals("city")) {
        newAddress.setCity(value.toString());
      }

    });

    // If Status is active -> deactivate all other active addresses in DB and set
    if (newAddress.getStatus().equals(AddressStatus.ACTIVE)) {
      List<Address> allAddressesOfPerson = addressDAO.findAllByPersonId(newAddress.getPersonId());
      allAddressesOfPerson.forEach((address) -> {
        if (address.getStatus().equals(AddressStatus.ACTIVE)) {
          address.setStatus(AddressStatus.INACTIVE);
          addressDAO.save(address);
        }
      });
    }

    return addressDAO.save(newAddress);
  }

  @Transactional
  public Address update(int addressId, int volunteerId, Map<String, Object> fields) {
    Address dbAddress = addressDAO.findById(addressId);

    if (dbAddress.getPersonId() != volunteerDAO.findById(volunteerId).getPerson().getId()) {
      throw new AddressInvalidFormatException(
          "Volunteer has no address with id: '" + addressId + "'");
    }

    fields.forEach((key, value) -> {
      if (key.toLowerCase().equals("status")) {

        // If Status is active -> deactivate all other active addresses in DB and set
        // status to active
        if (value.toString().toLowerCase().equals("active")) {
          List<Address> allAddressesOfPerson = addressDAO.findAllByPersonId(dbAddress.getPersonId());
          allAddressesOfPerson.forEach((address) -> {
            if (address.getStatus().equals(AddressStatus.ACTIVE)) {
              address.setStatus(AddressStatus.INACTIVE);
              addressDAO.save(address);
            }
          });
          dbAddress.setStatus(AddressStatus.ACTIVE);
        }

        // Otherwise set it to inactive
        if (value.toString().toLowerCase().equals("inactive")) {
          dbAddress.setStatus(AddressStatus.INACTIVE);
        }
      }

      if (key.toLowerCase().equals("name")) {
        dbAddress.setName(value.toString());
      }
      if (key.toLowerCase().equals("careof")) {
        dbAddress.setCareof(value.toString());
      }
      if (key.toLowerCase().equals("country")) {
        Country dbCountry = countryDAO.findByName(value.toString());
        dbAddress.setCountry(dbCountry);
      }
      if (key.toLowerCase().equals("state")) {
        dbAddress.setState(value.toString());
      }
      if (key.toLowerCase().equals("street")) {
        dbAddress.setStreet(value.toString());
      }
      if (key.toLowerCase().equals("postalcode")) {
        dbAddress.setPostalcode(value.toString());
      }
      if (key.toLowerCase().equals("city")) {
        dbAddress.setCity(value.toString());
      }

    });

    return addressDAO.save(dbAddress);
  }

  @Transactional
  public void deleteById(int addressId, int volunteerId) {
    Address dbAddress = addressDAO.findById(addressId);
    if (dbAddress.getPersonId() != volunteerDAO.findById(volunteerId).getPerson().getId()) {
      throw new AddressInvalidFormatException(
          "Volunteer has no address with id: '" + addressId + "'");
    }
    addressDAO.deleteById(addressId);
  }

}
