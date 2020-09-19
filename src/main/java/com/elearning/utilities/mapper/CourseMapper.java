package com.elearning.utilities.mapper;

import com.elearning.dto.CourseDTO;
import com.elearning.dto.TargetDTO;
import com.elearning.dto.VideoDTO;
import com.elearning.entity.Categories;
import com.elearning.entity.Courses;
import com.elearning.entity.Targets;
import com.elearning.entity.Videos;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static CourseDTO toCourseDTO(Courses courses) {
        CourseDTO dto = new CourseDTO();
        dto.setId(courses.getId());
        dto.setContent(courses.getContent());
        dto.setDescription(courses.getDescription());
        dto.setDiscount(courses.getDiscount());
        dto.setHourCount(courses.getHourCount());
        dto.setImage(courses.getImage());
        dto.setLastUpdate(courses.getLastUpdate());
        dto.setLecturesCount(courses.getLecturesCount());
        dto.setPrice(courses.getPrice());
        dto.setPromotionPrice(courses.getPromotionPrice());
        dto.setTitle(courses.getTitle());
        dto.setViewCount(courses.getViewCount());
        dto.setCategories(courses.getCategories().getTitle());
        List<TargetDTO> dtos = new ArrayList<>();
        for (Targets target: courses.getTargets()) {
            dtos.add(TargetMapper.toTargetDTO(target));
        }
        List<VideoDTO> videos = new ArrayList<>();
        for (Videos video: courses.getVideos()) {
            videos.add(VideoMapper.toVideoDTO(video));
        }
        dto.setTargets(dtos);
        dto.setVideos(videos);
        return dto;
    }

    public static Courses toCourses(CourseDTO dto, Categories categories){
        Courses courses = new Courses();
        courses.setContent(dto.getContent());
        courses.setDescription(dto.getDescription());
        courses.setDiscount(dto.getDiscount());
        courses.setHourCount(dto.getHourCount());
        courses.setImage(dto.getImage());
        courses.setLastUpdate(dto.getLastUpdate());
        courses.setLecturesCount(dto.getLecturesCount());
        courses.setPrice(dto.getPrice());
        courses.setPromotionPrice(dto.getPromotionPrice());
        courses.setTitle(dto.getTitle());
        courses.setViewCount(dto.getViewCount());
        courses.setCategories(categories);
        return courses;
    }
}
