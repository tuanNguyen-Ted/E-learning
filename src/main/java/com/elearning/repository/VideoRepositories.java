package com.elearning.repository;

import com.elearning.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepositories extends JpaRepository<Videos, Integer> {
}
