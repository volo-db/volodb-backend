package dev.urner.volodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.urner.volodb.dao.ContactTypeDAO;
import dev.urner.volodb.entity.ContactType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactTypeService {

  private final ContactTypeDAO contactTypeDAO;

  public List<ContactType> findAll() {
    return contactTypeDAO.findAll();
  }

  public ContactType findById(int contactTypeId) {
    return contactTypeDAO.findById(contactTypeId);
  }

}
