package com.nology.SpringAPIHalfwayChallenge.controller;

import com.nology.SpringAPIHalfwayChallenge.entity.CourseModule;
import com.nology.SpringAPIHalfwayChallenge.entity.Message;
import com.nology.SpringAPIHalfwayChallenge.exceptions.ResourceNotFoundException;
import com.nology.SpringAPIHalfwayChallenge.repository.CourseModuleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseModuleController {

    private final CourseModuleRepository moduleRepo = new CourseModuleRepository();

    //GET all modules
    @GetMapping("/modules")
    public ResponseEntity<List<CourseModule>> indexModules() {
        return ResponseEntity.status(HttpStatus.OK).body(moduleRepo.findAll());
    }

    //GET module using selected id
    @GetMapping("/modules/{id}")
    public ResponseEntity<CourseModule> showModule(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(moduleRepo.findModuleById(id));
    }

    //POST new module
    @PostMapping("/modules")
    public ResponseEntity<Object> createModule(@RequestBody CourseModule newModule) {
        moduleRepo.addModule(newModule);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Message("Successfully created new module"));
    }

    //PUT: update module using selected id
    @PutMapping("/modules/{id}")
    public ResponseEntity<Object> updateModule(@PathVariable int id, @RequestBody CourseModule newModule) throws ResourceNotFoundException {
        moduleRepo.updateModuleById(id, newModule);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Message("Successfully updated module with id: " + id));
    }

    //DELETE module using selected id
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Message> deleteModule(@PathVariable int id) throws ResourceNotFoundException {
        moduleRepo.deleteModuleById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Message("Successfully deleted module with id: " + id));
    }
}
