package dev.urner.volodb.service;

import java.util.List;

import dev.urner.volodb.entity.Project;

public interface ProjectService {
    List<Project> findAll();

    Project findByProjectId(int theProjectId);

    Project save(Project theProject);

    void deleteById(int theId);
}
