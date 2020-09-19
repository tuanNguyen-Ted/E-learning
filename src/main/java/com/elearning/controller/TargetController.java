package com.elearning.controller;

import com.elearning.dto.TargetDTO;
import com.elearning.services.TargetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path={"/api/targets"})
public class TargetController {
    private final TargetServices targetServices;

    @Autowired
    TargetController(TargetServices targetServices){
        this.targetServices = targetServices;
    }


    @GetMapping
    Object getAll(){
        try{
            List<TargetDTO> dtos= targetServices.findAll();
            return new ResponseEntity<Object>(dtos, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No Targets Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = { "" })
    public Object addTarget(@RequestBody TargetDTO dto) {
        try {
            this.targetServices.add(dto);
            return new ResponseEntity<String>("Successfully added new target.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new target. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = { "" })
    public Object updateTarget(@RequestBody TargetDTO dto) {
        try {
            this.targetServices.update(dto);
            return new ResponseEntity<String>("Successfully updated target.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating target. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeTarget(@PathVariable("id") int id) {
        try {
            this.targetServices.delete(id);
            return new ResponseEntity<String>("Target removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing target. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
