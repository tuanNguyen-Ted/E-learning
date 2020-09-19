package com.elearning.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String url;
    private int timeCount;
    private int courses_id;

    @ManyToOne
    @JoinColumn(name= "courses_id", updatable = false, insertable = false)
    private Courses courses;
}
