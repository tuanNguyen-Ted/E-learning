package com.elearning.repository;

import com.elearning.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepositoies extends JpaRepository<Courses, Integer> {
}
