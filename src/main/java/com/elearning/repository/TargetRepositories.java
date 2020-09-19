package com.elearning.repository;

import com.elearning.entity.Targets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepositories extends JpaRepository<Targets, Integer> {
}
