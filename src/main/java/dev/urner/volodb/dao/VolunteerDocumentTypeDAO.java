package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerDocumentType;

@Repository
public interface VolunteerDocumentTypeDAO extends ListCrudRepository<VolunteerDocumentType, Integer> {

  public List<VolunteerDocumentType> findAll();

  public VolunteerDocumentType findById(int id);

  public VolunteerDocumentType save(VolunteerDocumentType documentType);

  public void deleteById(int id);

  public VolunteerDocumentType findByName(String name);

}
