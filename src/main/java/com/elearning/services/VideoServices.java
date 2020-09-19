package com.elearning.services;


import com.elearning.dto.VideoDTO;

import java.util.List;

public interface VideoServices {
    void add(VideoDTO dto);

    List<VideoDTO> findAll();

    VideoDTO findById(int id);

    void update(VideoDTO dto);

    void delete(int id);
}
