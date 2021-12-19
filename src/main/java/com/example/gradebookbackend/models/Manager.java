package com.example.gradebookbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "managers", schema = "public")
public class Manager {

    @Id
    @Column(name = "manager_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerID;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    public String getSurnameName() {
        return this.surname + " " + this.getName();
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manager")
    private Set<Project> projects;

    public String getProjectsString() {
        String projectString = "";
        for (Project p : projects) {
            projectString += p.getName() + ", ";
        }
        return projectString.isEmpty() ? projectString : projectString.substring(0, projectString.length() - 2);
    }

}
