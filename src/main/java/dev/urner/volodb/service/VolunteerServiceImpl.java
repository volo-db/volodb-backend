package dev.urner.volodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.urner.volodb.dao.VolunteerDAO;
import dev.urner.volodb.entity.Volunteer;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private VolunteerDAO volunteerDAO;

    @Autowired
    public VolunteerServiceImpl(VolunteerDAO theVolunteerDAO) {
        volunteerDAO = theVolunteerDAO;
    }

    @Override
    public List<Volunteer> findAll() {
        return volunteerDAO.findAll();
    }

    @Override
    public Volunteer findById(int theId) {
        return volunteerDAO.findById(theId);
    }

    @Transactional
    @Override
    public Volunteer save(Volunteer theVolunteer) {
        return volunteerDAO.save(theVolunteer);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        volunteerDAO.deleteById(theId);
    }
}






