package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository  extends JpaRepository<Manager, Integer> {

    public List<Manager> findAll();
}