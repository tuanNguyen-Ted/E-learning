package com.elearning.utilities.mapper;

import com.elearning.dto.CategoryDTO;
import com.elearning.entity.Categories;

public class CategoryMapper {
    public static Categories toCategory(CategoryDTO dto){
        Categories categories = new Categories();
        categories.setIcon(dto.getIcon());
        categories.setTitle(dto.getTitle());
        return  categories;
    }
    public static CategoryDTO toCategoryDTO(Categories categories){
        CategoryDTO dto = new CategoryDTO();
        dto.setIcon(categories.getIcon());
        dto.setTitle(categories.getTitle());
        return  dto;
    }
}
