package com.elearning.dto;

import lombok.Data;

@Data
public class VideoDTO {
    private int id;
    private String title;
    private String url;
    private int timeCount;
    private int courses_id;
}
