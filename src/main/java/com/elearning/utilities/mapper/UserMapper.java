package com.elearning.utilities.mapper;

import com.elearning.dto.CourseDTO;
import com.elearning.dto.UsersDTO;
import com.elearning.dto.VideoDTO;
import com.elearning.entity.Courses;
import com.elearning.entity.Roles;
import com.elearning.entity.Users;
import com.elearning.entity.Videos;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UsersDTO toUserDTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setAvatar(user.getAvatar());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRoleDesc(user.getRole().getDescription());
        List<CourseDTO> dtos = new ArrayList<>();
        for (Courses item: user.getCourses()) {
            dtos.add(CourseMapper.toCourseDTO(item));
        }
        dto.setCourseDTOS(dtos);
        return dto;
    }

    public static Users toUser(UsersDTO dto, Roles roles) {
        Users user = new Users();
        user.setId(dto.getId());
        user.setFullName(dto.getFullName());
        user.setAvatar(dto.getAvatar());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));

        user.setRole(roles);
        return user;
    }
}