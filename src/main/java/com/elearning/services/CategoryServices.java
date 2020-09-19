package com.elearning.services;

import com.elearning.dto.CategoryDTO;

import java.util.List;

public interface CategoryServices {
    void add(CategoryDTO dto);

    List<CategoryDTO> findAll();

    CategoryDTO findById(int id);

    void update(CategoryDTO categoryDto);

    void delete(int id);
}
