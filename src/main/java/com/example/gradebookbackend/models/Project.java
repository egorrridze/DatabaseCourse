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
@Table(name = "projects", schema = "public")
public class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectID;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private Integer budget;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<Developer> developers;

    public String getDevelopersString() {
        String developersString = "";
        for (Developer p : developers) {
            developersString += p.getSurname() + " " + p.getName() + ", ";
        }
        return developersString.isEmpty() ? developersString : developersString.substring(0, developersString.length() - 2);

    }
}
