package com.elearning.services.impl;

import com.elearning.dto.VideoDTO;
import com.elearning.entity.Videos;
import com.elearning.repository.VideoRepositories;
import com.elearning.services.VideoServices;
import com.elearning.utilities.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class VideoServicesImpl implements VideoServices {
    private final VideoRepositories videoRepositories;

    @Autowired
    VideoServicesImpl(VideoRepositories videoRepositories) {
        this.videoRepositories = videoRepositories;
    }


    @Override
    public void add(VideoDTO dto) {
        videoRepositories.save(VideoMapper.toVideos(dto));
    }

    @Override
    public List<VideoDTO> findAll() {
        List<Videos> list = videoRepositories.findAll();
        List<VideoDTO> dtos = new ArrayList<>();
        for (Videos video : list) {
            dtos.add(VideoMapper.toVideoDTO(video));
        }
        return dtos;
    }

    @Override
    public VideoDTO findById(int id) {
        return VideoMapper.toVideoDTO(videoRepositories.findById(Integer.valueOf(id)).get());
    }

    @Override
    public void update(VideoDTO dto) {
        Videos video = videoRepositories.findById(dto.getId()).get();
        video.setTimeCount(dto.getTimeCount());
        video.setTitle(dto.getTitle());
        video.setUrl(dto.getUrl());
        videoRepositories.save(video);
    }

    @Override
    public void delete(int id) {
        videoRepositories.deleteById(id);
    }
}
