package com.elearning.controller;

import com.elearning.dto.CourseDTO;
import com.elearning.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path={"/api/courses"})
public class CourseController {
    private final CourseServices courseServices;

    @Autowired
    public CourseController(CourseServices courseServices){
        this.courseServices = courseServices;
    }


    @GetMapping
    Object getAll(){
        try{
            List<CourseDTO> dtos= courseServices.findAll();
            return new ResponseEntity<Object>(dtos, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No Courses Found", HttpStatus.BAD_REQUEST);
    }

//    ADD
    @PostMapping(path = { "" })
    public Object addCourse(@RequestBody CourseDTO dto) {
        try {
            this.courseServices.add(dto);
            return new ResponseEntity<String>("Successfully added new course.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new course. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getCourse(@PathVariable("id") int id) {
        try {
            CourseDTO dto = this.courseServices.findById(id);
            return new ResponseEntity<Object>(dto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Course not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateCourse(@RequestBody CourseDTO dto) {
        try {
            this.courseServices.update(dto);
            return new ResponseEntity<String>("Successfully updated course.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating course. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeCourse(@PathVariable("id") int id) {
        try {
            this.courseServices.delete(id);
            return new ResponseEntity<String>("Course removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing course. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
