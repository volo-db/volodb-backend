package dev.urner.volodb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.urner.volodb.entity.Volunteer;

import java.util.List;

@Repository
public class VolunteerDAOJpaImpl implements VolunteerDAO {

    // define field for entitymanager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired
    public VolunteerDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<Volunteer> findAll() {

        // create a query
        TypedQuery<Volunteer> theQuery = entityManager.createQuery("from Volunteer", Volunteer.class);

        // execute query and get result list
        List<Volunteer> volunteers = theQuery.getResultList();

        // return the results
        return volunteers;
    }

    @Override
    public Volunteer findById(int theId) {

        // get volunteer
        Volunteer theVolunteer = entityManager.find(Volunteer.class, theId);

        // return volunteer
        return theVolunteer;
    }

    @Override
    public Volunteer save(Volunteer theVolunteer) {

        // save volunteer
        Volunteer dbVolunteer = entityManager.merge(theVolunteer);

        // return the dbVolunteer
        return dbVolunteer;
    }

    @Override
    public void deleteById(int theId) {

        // find volunteer by id
        Volunteer theVolunteer = entityManager.find(Volunteer.class, theId);

        // remove volunteer
        entityManager.remove(theVolunteer);
    }
}











