package com.elearning.controller;

import com.elearning.dto.UsersDTO;
import com.elearning.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/users"})
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(path={""})
    public Object getUser() {
        try {
            List<UsersDTO> listDto = userServices.getAll();
            if(listDto != null)
            return new ResponseEntity<Object>(listDto, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No users found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path={""})
    public Object addUser(@RequestBody UsersDTO dto){
        try {
            userServices.add(dto);
            return new ResponseEntity<String>("Successfully added new user.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new user. \nError details: %s", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path={"/userid={id}"})
    public Object findById(@PathVariable("id") int id){
        try {
           UsersDTO dto = userServices.findById(id);
            return new ResponseEntity<Object>(dto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No user found", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = { "/{id}" })
    public Object removeUser(@PathVariable("id") int id) {
        try {
            this.userServices.delete(id);
            return new ResponseEntity<String>("User removed.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing user. \nError details: %s", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = { "" })
    public Object updateUser(@RequestBody UsersDTO dto) {
        try {
            this.userServices.update(dto);
            return new ResponseEntity<String>("Successfully updated user.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating user. \nError details: %s", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path={"/search={keyword}"})
    public Object search(@PathVariable("keyword") String keyword){
        try {
           List<UsersDTO> dtos =  this.userServices.search(keyword);
            return new ResponseEntity<Object>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No Users Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path={"/{id}"})
    public Object addCourse(@PathVariable int id, @RequestBody int courseId){
        try {
           userServices.addCourses(id, courseId);
            return new ResponseEntity<String>("Successfully added course for user.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No Users Found", HttpStatus.BAD_REQUEST);
    }
}
