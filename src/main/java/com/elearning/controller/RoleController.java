package com.elearning.controller;

import com.elearning.dto.RoleDTO;
import com.elearning.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path={"/api/roles"})
public class RoleController {
    private final RoleServices roleServices;

    @Autowired
    public RoleController(RoleServices roleServices){
        this.roleServices = roleServices;
    }

    @PostMapping(path = { "" })
    public Object addRole(@RequestBody RoleDTO dto) {
        try {
            this.roleServices.add(dto);
            return new ResponseEntity<String>("Successfully added new role.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new role. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "" })
    public Object getRoles() {
        try {
            List<RoleDTO> list = this.roleServices.getAll();
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No roles exist.", HttpStatus.NO_CONTENT);
        }
    }



    @GetMapping(path = { "/{id}" })
    public Object getRole(@PathVariable("id") int id) {
        try {
            RoleDTO dto = this.roleServices.findById(id);
            return new ResponseEntity<Object>(dto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Role not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateRole(@RequestBody RoleDTO dto) {
        try {
            this.roleServices.update(dto);
            return new ResponseEntity<String>("Successfully updated role.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating role. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeRole(@PathVariable("id") int id) {
        try {
            this.roleServices.delete(id);
            return new ResponseEntity<String>("Role removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing role. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
