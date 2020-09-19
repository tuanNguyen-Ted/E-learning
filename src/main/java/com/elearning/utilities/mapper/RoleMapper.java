package com.elearning.utilities.mapper;

import com.elearning.dto.RoleDTO;
import com.elearning.entity.Roles;

public class RoleMapper {
    public static RoleDTO toRoleDTO(Roles roles) {
        RoleDTO dto = new RoleDTO();
        dto.setId(roles.getId());
        dto.setDescription(roles.getDescription());
        dto.setName(roles.getName());
        return dto;
    }

    public static Roles toRole(RoleDTO dto) {
        Roles roles = new Roles();
        roles.setDescription(dto.getDescription());
        roles.setName(dto.getName());
        return  roles;
    }
}
