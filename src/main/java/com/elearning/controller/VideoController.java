package com.elearning.controller;

import com.elearning.dto.VideoDTO;
import com.elearning.services.VideoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path={"/api/videos"})
public class VideoController {
    private final VideoServices videoServices;

    @Autowired
    VideoController(VideoServices videoServices){
        this.videoServices = videoServices;
    }


    @GetMapping
    Object getAll(){
        try{
            List<VideoDTO> dtos= videoServices.findAll();
            return new ResponseEntity<Object>(dtos, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No Videos Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = { "" })
    public Object addVideo(@RequestBody VideoDTO dto) {
        try {
            this.videoServices.add(dto);
            return new ResponseEntity<String>("Successfully added new video.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new video. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getVideo(@PathVariable("id") int id) {
        try {
            VideoDTO dto = this.videoServices.findById(id);
            return new ResponseEntity<Object>(dto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Video not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateVideo(@RequestBody VideoDTO dto) {
        try {
            this.videoServices.update(dto);
            return new ResponseEntity<String>("Successfully updated video.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating video. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeVideo(@PathVariable("id") int id) {
        try {
            this.videoServices.delete(id);
            return new ResponseEntity<String>("Video removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing video. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
