package com.elearning.controller;

import com.elearning.dto.CategoryDTO;
import com.elearning.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path={"/api/categories"})
public class CategoryController {
    private final CategoryServices categoryServices;

    @Autowired
    CategoryController(CategoryServices categoryServices){
        this.categoryServices = categoryServices;
    }


    @GetMapping
    Object getAll(){
        try{
            List<CategoryDTO> dtos= categoryServices.findAll();
            return new ResponseEntity<Object>(dtos, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No Categories Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = { "" })
    public Object addCategory(@RequestBody CategoryDTO dto) {
        try {
            this.categoryServices.add(dto);
            return new ResponseEntity<String>("Successfully added new category.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getCategory(@PathVariable("id") int id) {
        try {
            CategoryDTO dto = this.categoryServices.findById(id);
            return new ResponseEntity<Object>(dto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("Category not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateCategory(@RequestBody CategoryDTO dto) {
        try {
            this.categoryServices.update(dto);
            return new ResponseEntity<String>("Successfully updated category.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeCategory(@PathVariable("id") int id) {
        try {
            this.categoryServices.delete(id);
            return new ResponseEntity<String>("Category removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing category. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
