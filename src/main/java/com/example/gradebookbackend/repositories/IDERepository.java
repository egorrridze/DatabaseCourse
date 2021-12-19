package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.IDE;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IDERepository extends CrudRepository<IDE, Integer> {

    public List<IDE> findByOrderByIDEidAsc();

}