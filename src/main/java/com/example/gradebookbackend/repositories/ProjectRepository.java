package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    public List<Project> findAll();
}
