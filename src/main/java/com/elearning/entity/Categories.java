package com.elearning.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Size(min=6,max=20)
    private String title;
    private String icon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<Courses> courses;
}
