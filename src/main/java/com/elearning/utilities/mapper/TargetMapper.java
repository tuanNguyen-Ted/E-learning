package com.elearning.utilities.mapper;

import com.elearning.dto.TargetDTO;
import com.elearning.entity.Targets;

public class TargetMapper {
    public static TargetDTO toTargetDTO(Targets targets) {
        TargetDTO dto = new TargetDTO();
        dto.setTitle(targets.getTitle());
        dto.setCourses_id(targets.getCourses_id());
        return dto;
    }
    public static Targets toTarget(TargetDTO dto) {
        Targets target = new Targets();
        target.setId(target.getId());
        target.setTitle(dto.getTitle());
        target.setCourses_id(dto.getCourses_id());
        return target;
    }
}
