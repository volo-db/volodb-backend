package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ContactDAO;
import dev.urner.volodb.dao.ContactTypeDAO;
import dev.urner.volodb.dao.VolunteerDAO;
import dev.urner.volodb.entity.Contact;
import dev.urner.volodb.entity.ContactType;
import dev.urner.volodb.exception.ContactTypeNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactService {
  private final ContactDAO contactDAO;
  private final ContactTypeDAO contactTypeDAO;
  private final VolunteerDAO volunteerDAO;

  public List<Contact> findAll() {
    return contactDAO.findAll();
  }

  public Contact findById(int contactId) {
    return contactDAO.findById(contactId);
  }

  @Transactional
  public Contact save(int volunteerId, Map<String, Object> fields) {
    Contact newContact = new Contact();
    newContact.setPersonId(volunteerDAO.findById(volunteerId).getPerson().getId());

    fields.forEach((key, value) -> {
      if (key.toLowerCase().equals("type")) {
        ContactType dbContactType = contactTypeDAO.findByName(value.toString().toLowerCase());
        if (dbContactType == null) {
          throw new ContactTypeNotFoundException("Contact with type: \"" + value.toString() + "\" not found.");
        }

        newContact.setType(dbContactType);
      }

      if (key.toLowerCase().equals("value")) {
        newContact.setValue(value.toString());
      }
    });

    return contactDAO.save(newContact);
  }

  @Transactional
  public Contact update(int contactId, Map<String, Object> fields) {
    Contact dbContact = contactDAO.findById(contactId);

    fields.forEach((key, value) -> {
      if (key.toLowerCase().equals("type")) {
        ContactType dbContactType = contactTypeDAO.findByName(value.toString().toLowerCase());
        if (dbContactType != null) {
          dbContact.setType(dbContactType);
        }
      }

      if (key.toLowerCase().equals("value")) {
        dbContact.setValue(value.toString());
      }
    });

    return contactDAO.save(dbContact);
  }

  @Transactional
  public void deleteById(int contactId) {
    contactDAO.deleteById(contactId);
  }

  public List<Contact> findAllByVolunteerId(int volunteerId) {
    int personId = volunteerDAO.findById(volunteerId).getPerson().getId();
    return contactDAO.findAllByPersonId(personId);
  }

}
