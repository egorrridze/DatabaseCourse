package com.example.gradebookbackend.repositories;


import com.example.gradebookbackend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    public User getUserByUsername(@Param("username") String username);

}
