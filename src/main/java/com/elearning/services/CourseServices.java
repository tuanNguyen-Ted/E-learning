package com.elearning.services;

import com.elearning.dto.CourseDTO;

import java.util.List;

public interface CourseServices {
    void add(CourseDTO dto);

    List<CourseDTO> findAll();

    CourseDTO findById(int id);

    void update(CourseDTO dto);

    void delete(int id);
}
