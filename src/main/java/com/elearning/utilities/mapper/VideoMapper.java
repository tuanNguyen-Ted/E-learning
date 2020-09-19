package com.elearning.utilities.mapper;

import com.elearning.dto.VideoDTO;
import com.elearning.entity.Videos;

public class VideoMapper {
    public static VideoDTO toVideoDTO(Videos video) {
        VideoDTO dto = new VideoDTO();
        dto.setTimeCount(video.getTimeCount());
        dto.setTitle(video.getTitle());
        dto.setUrl(video.getUrl());
        dto.setCourses_id(video.getCourses_id());
        return dto;
    }
    public static Videos toVideos(VideoDTO dto) {
        Videos video = new Videos();
        video.setTimeCount(dto.getTimeCount());
        video.setTitle(dto.getTitle());
        video.setUrl(dto.getUrl());
        video.setCourses_id(dto.getCourses_id());
        return video;
    }
}
