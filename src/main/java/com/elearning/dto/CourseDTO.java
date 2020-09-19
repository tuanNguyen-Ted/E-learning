package com.elearning.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CourseDTO {
    private int id;

    private String title;

    private String image;

    private int lecturesCount;

    private int hourCount;

    private int viewCount;

    private double price;

    private int discount;

    private double promotionPrice;

    private String description;

    private String content;

    private Date lastUpdate;

    private String categories;

    private List<TargetDTO> targets;

    private List<VideoDTO> videos;
}
