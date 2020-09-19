package com.elearning.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersDTO {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String avatar;
    private String phone;
    private String roleDesc;
    private List<CourseDTO> courseDTOS;
}
