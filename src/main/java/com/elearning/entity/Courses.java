package com.elearning.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min=6, max= 50)
    private String title;
    private String image;
    @Size(max=1000)
    private int lecturesCount;
    private int hourCount;
    private int viewCount;
    private double price;
    private int discount;
    private double promotionPrice;
    private String description;
    private String content;
    private Date lastUpdate;


    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Users> users;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Categories categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Videos> videos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Targets> targets;

}
