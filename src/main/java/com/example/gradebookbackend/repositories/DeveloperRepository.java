package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Developer;
import com.example.gradebookbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeveloperRepository extends CrudRepository<Developer, Integer> {

    public List<Developer> findByOrderByDeveloperIDAsc();

    public Developer getDeveloperByDeveloperID(Integer id);

    public void deleteDeveloperByDeveloperID(Integer id);

    @Query(value = "select * from developers d where d.name like %:keyword% or d.surname like %:keyword% or d.position like %:keyword%", nativeQuery = true)
    List<Developer> findByKeyword(@Param("keyword") String keyword);

}
