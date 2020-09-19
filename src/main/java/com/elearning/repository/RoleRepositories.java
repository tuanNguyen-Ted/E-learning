package com.elearning.repository;

import com.elearning.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepositories extends JpaRepository<Roles, Integer> {
    Roles findByDescription(String desc);

}
