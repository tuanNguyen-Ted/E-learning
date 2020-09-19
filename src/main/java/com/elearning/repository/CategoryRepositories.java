package com.elearning.repository;

import com.elearning.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositories extends JpaRepository<Categories, Integer> {

    Categories findByTitle(String tittle);
}
