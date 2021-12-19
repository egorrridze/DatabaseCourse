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
@Table(name = "ides", schema = "public")
public class IDE {

    @Id
    @Column(name = "ide_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDEid;

    @Column(name = "name")
    private String name;

    @Column(name = "license")
    private String license;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "IDE")
    private Set<Developer> developers;
}
