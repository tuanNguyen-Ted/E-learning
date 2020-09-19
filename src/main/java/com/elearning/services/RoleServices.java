package com.elearning.services;

import com.elearning.dto.RoleDTO;
import com.elearning.entity.Roles;

import java.util.List;

public interface RoleServices {
    List<RoleDTO> getAll();
    void add(RoleDTO dto);

    RoleDTO findById(int id);

    void update(RoleDTO dto);

    void delete(int id);
}
