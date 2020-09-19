package com.elearning.services;

import com.elearning.dto.UsersDTO;
import com.elearning.entity.Users;

import java.util.List;

public interface UserServices {
    List<UsersDTO> getAll();
    void add(UsersDTO dto);


    UsersDTO findById(int id);

    void update(UsersDTO dto);

    void delete(int id);

    List<UsersDTO> search(String keyword);
    void addCourses(int userId, int coursesId);
}
