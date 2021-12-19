package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Manager;
import com.example.gradebookbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository  extends JpaRepository<Manager, Integer> {

    public List<Manager> findByOrderByManagerIDAsc();

    public Manager getManagerByManagerID(Integer id);

    public void deleteManagerByManagerID(Integer id);

    @Query(value = "select * from managers m where m.name like %:keyword% or m.surname like %:keyword%", nativeQuery = true)
    List<Manager> findByKeyword(@Param("keyword") String keyword);
}