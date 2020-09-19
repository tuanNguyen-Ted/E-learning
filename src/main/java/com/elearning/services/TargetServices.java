package com.elearning.services;

import com.elearning.dto.TargetDTO;

import java.util.List;

public interface TargetServices {
    void add(TargetDTO dto);

    List<TargetDTO> findAll();

    void update(TargetDTO dto);

    void delete(int id);
}
