package dev.urner.volodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.VolunteerStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class VolunteerStatusDAOJpaImpl implements VolunteerStatusDAO {

  // define field for entitymanager
  private EntityManager entityManager;

  // set up constructor injection
  @Autowired
  public VolunteerStatusDAOJpaImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public VolunteerStatus findByName(String theName) {

    // create a query
    TypedQuery<VolunteerStatus> theQuery = entityManager
        .createQuery("SELECT vs FROM VolunteerStatus vs WHERE vs.name = :theName", VolunteerStatus.class)
        .setParameter("theName", theName);

    // execute query and get result list
    List<VolunteerStatus> volunteerStatus = theQuery.getResultList();

    // return the dbVolunteer
    return volunteerStatus.get(0);
  }

}
