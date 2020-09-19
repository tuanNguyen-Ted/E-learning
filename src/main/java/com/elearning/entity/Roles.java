package com.elearning.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Users> users;
    public Roles(){}
}
