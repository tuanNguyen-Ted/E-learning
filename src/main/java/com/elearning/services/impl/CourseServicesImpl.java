package com.elearning.services.impl;

import com.elearning.dto.CourseDTO;
import com.elearning.entity.Categories;
import com.elearning.entity.Courses;
import com.elearning.repository.CategoryRepositories;
import com.elearning.repository.CoursRepositoies;
import com.elearning.repository.TargetRepositories;
import com.elearning.services.CourseServices;
import com.elearning.utilities.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseServicesImpl implements CourseServices {
    private final CoursRepositoies coursRepositoies;

    @Autowired
    CategoryRepositories categoriesRepositories;
@Autowired
    TargetRepositories targetRepositories;
    @Autowired
    public CourseServicesImpl(CoursRepositoies coursRepositoies) {
        this.coursRepositoies = coursRepositoies;
    }


    @Override
    public void add(CourseDTO dto) {
        Categories category = categoriesRepositories.findByTitle(dto.getCategories());
        coursRepositoies.save(CourseMapper.toCourses(dto, category));
    }

    @Override
    public List<CourseDTO> findAll() {
        List<Courses> list = coursRepositoies.findAll();
        List<CourseDTO> dtos = new ArrayList<>();
        for (Courses courses : list) {
            dtos.add(CourseMapper.toCourseDTO(courses));
        }
        return dtos;
    }

    @Override
    public CourseDTO findById(int id) {
        Courses courses = coursRepositoies.findById(Integer.valueOf(id)).get();
        return CourseMapper.toCourseDTO(courses);
    }

    @Override
    public void update(CourseDTO dto) {
        Courses course = coursRepositoies.findById(Integer.valueOf(dto.getId())).get();
        course.setTitle(dto.getTitle());
        course.setImage(dto.getImage());
        course.setLecturesCount(dto.getLecturesCount());
        course.setHourCount(dto.getHourCount());
        course.setViewCount(dto.getViewCount());
        course.setPrice(dto.getPrice());
        course.setDiscount(dto.getDiscount());
        course.setPromotionPrice(dto.getPromotionPrice());
        course.setDescription(dto.getDescription());
        course.setContent(dto.getContent());
        course.setLastUpdate(dto.getLastUpdate());
        course.setCategories(categoriesRepositories.findByTitle(dto.getCategories()));
        this.coursRepositoies.save(course);
    }

    @Override
    public void delete(int id) {
        coursRepositoies.deleteById(id);
    }
}
