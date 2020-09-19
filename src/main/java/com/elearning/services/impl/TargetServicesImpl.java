package com.elearning.services.impl;

import com.elearning.dto.TargetDTO;
import com.elearning.entity.Targets;
import com.elearning.repository.TargetRepositories;
import com.elearning.services.TargetServices;
import com.elearning.utilities.mapper.TargetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServicesImpl implements TargetServices {
    private final TargetRepositories targetRepositories;

    @Autowired
    TargetServicesImpl(TargetRepositories targetRepositories) {
        this.targetRepositories = targetRepositories;
    }


    @Override
    public void add(TargetDTO dto) {
        targetRepositories.save(TargetMapper.toTarget(dto));
    }

    @Override
    public List<TargetDTO> findAll() {
        List<Targets> list = targetRepositories.findAll();
        List<TargetDTO> dtos = new ArrayList<>();
        for (Targets target : list) {
            dtos.add(TargetMapper.toTargetDTO(target));
        }
        return dtos;
    }

    @Override
    public void update(TargetDTO dto) {
        Targets target = targetRepositories.findById(dto.getId()).get();
        target.setTitle(dto.getTitle());
        targetRepositories.save(target);
    }

    @Override
    public void delete(int id) {
        targetRepositories.deleteById(id);
    }
}
