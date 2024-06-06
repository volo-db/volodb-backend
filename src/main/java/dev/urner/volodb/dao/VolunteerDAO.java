package dev.urner.volodb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import dev.urner.volodb.entity.Volunteer;

@Repository
public interface VolunteerDAO extends PagingAndSortingRepository<Volunteer, Integer> {

  Page<Volunteer> findAll(Pageable pageable);

  @Query("SELECT v FROM Volunteer v WHERE v.person.firstname LIKE %:name% OR v.person.lastname LIKE %:name%")
  Page<Volunteer> findByNameContaining(@Param("name") String searchQuery, Pageable pageable);

  Volunteer findById(int id);

  Volunteer save(Volunteer volunteer);

  void deleteById(int id);
}
