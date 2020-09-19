package com.elearning.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Targets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String title;
    @NotNull
    private int courses_id;

    @ManyToOne
    @JoinColumn(name = "courses_id", updatable = false, insertable = false)
    private Courses courses;
}
