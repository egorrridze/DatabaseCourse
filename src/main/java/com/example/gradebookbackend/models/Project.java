package com.example.gradebookbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects", schema = "public")
public class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectID;

    @Column(name = "manager_id")
    private Integer managerID;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private Integer budget;

}
