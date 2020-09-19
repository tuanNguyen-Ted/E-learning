package com.elearning.services.impl;

import com.elearning.dto.RoleDTO;
import com.elearning.entity.Roles;
import com.elearning.repository.RoleRepositories;
import com.elearning.services.RoleServices;
import com.elearning.utilities.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleServicesImpl implements RoleServices {
    private final RoleRepositories roleRepositories;

    @Autowired
    public RoleServicesImpl(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    public List<RoleDTO> getAll() {
        List<Roles> list = roleRepositories.findAll();
        List<RoleDTO> dtos = new LinkedList<>();
        for (Roles roles: list) {
            dtos.add(RoleMapper.toRoleDTO(roles));
        }
        return dtos;
    }

    @Override
    public void add(RoleDTO dto) {
        roleRepositories.save(RoleMapper.toRole(dto));
    }

    @Override
    public RoleDTO findById(int id) {
        return RoleMapper.toRoleDTO(this.roleRepositories.findById(id).get());
    }

    @Override
    public void update(RoleDTO dto) {
        Roles role = this.roleRepositories.findById(dto.getId()).get();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        this.roleRepositories.save(role);
    }

    @Override
    public void delete(int id) {
        this.roleRepositories.deleteById(id);
    }
}
