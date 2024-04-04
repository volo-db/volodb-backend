package dev.urner.volodb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import dev.urner.volodb.entity.Volunteer;

@Repository
public interface VolunteerDAO extends PagingAndSortingRepository<Volunteer, Integer> {

  Page<Volunteer> findAll(Pageable pageable);

  Volunteer findById(int id);

  Volunteer save(Volunteer volunteer);

  void deleteById(int id);
}
