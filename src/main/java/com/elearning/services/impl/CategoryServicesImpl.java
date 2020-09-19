package com.elearning.services.impl;

import com.elearning.dto.CategoryDTO;
import com.elearning.entity.Categories;
import com.elearning.repository.CategoryRepositories;
import com.elearning.services.CategoryServices;
import com.elearning.utilities.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServicesImpl implements CategoryServices {
    private final CategoryRepositories categoryRepositories;

    @Autowired
    CategoryServicesImpl(CategoryRepositories categoryRepositories) {
        this.categoryRepositories = categoryRepositories;
    }

    @Override
    public void add(CategoryDTO dto) {
        this.categoryRepositories.save(CategoryMapper.toCategory(dto));
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Categories> list = categoryRepositories.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();
        for (Categories categories : list) {
            dtos.add(CategoryMapper.toCategoryDTO(categories));
        }
        return dtos;
    }

    @Override
    public CategoryDTO findById(int id) {
        return CategoryMapper.toCategoryDTO(categoryRepositories.findById(Integer.valueOf(id)).get());
    }

    @Override
    public void update(CategoryDTO dto) {
        Categories categories = categoryRepositories.findById(dto.getId()).get();
        categories.setTitle(dto.getTitle());
        categories.setIcon(dto.getIcon());
        categoryRepositories.save(categories);
    }

    @Override
    public void delete(int id) {
        categoryRepositories.deleteById(id);
    }
}
