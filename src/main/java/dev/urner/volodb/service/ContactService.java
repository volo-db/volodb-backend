package dev.urner.volodb.service;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ContactDAO;
// import dev.urner.volodb.dao.ContactTypeDAO;
import dev.urner.volodb.entity.Contact;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
// import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactService {
  private final ContactDAO contactDAO;
  // private final ContactTypeDAO contactTypeDAO;

  public List<Contact> findAll() {
    return contactDAO.findAll();
  }

  public Contact findById(int contactId) {
    return contactDAO.findById(contactId);
  }

  @Transactional
  public Contact save(Contact contact) {
    return contactDAO.save(contact);
  }

  // @Transactional
  // public Contact update(int contactId, Map<String, Object> fields) {
  // Contact dbContact = contactDAO.findById(contactId);

  // fields.forEach((key, value) -> {
  // });
  // }

  @Transactional
  public void deleteById(int contactId) {
    contactDAO.deleteById(contactId);
  }

  public List<Contact> findAllByPersonId(int personId) {
    return contactDAO.findAllByPersonId(personId);
  }

}
