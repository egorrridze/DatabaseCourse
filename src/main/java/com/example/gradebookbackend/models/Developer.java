package com.example.gradebookbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developers", schema = "public")
public class Developer {
    @Id
    @Column(name = "developer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer developerID;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ide_id")
    private IDE IDE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "developers_projects",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"),
            schema = "public"
    )
    private Set<Project> projects;

    public String getSurnameName() {
        return this.surname + " " + this.getName();
    }

    public String getProjectsString() {
        String projectString = "";
        for (Project p : projects) {
            projectString += p.getName() + ", ";
        }
        return projectString.isEmpty() ? projectString : projectString.substring(0, projectString.length() - 2);
    }


}
