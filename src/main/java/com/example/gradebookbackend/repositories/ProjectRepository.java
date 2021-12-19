package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    public List<Project> findByOrderByProjectIDAsc();

    public Project getProjectByProjectID(Integer id);

    public void deleteProjectByProjectID(Integer project_id);

    /*order by project_id*/
    @Query(value = "select * from projects p where p.name like %:keyword% ", nativeQuery = true)
    List<Project> findByKeyword(@Param("keyword") String keyword);

}
