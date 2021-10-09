package com.nology.SpringAPIHalfwayChallenge.repository;

import com.nology.SpringAPIHalfwayChallenge.entity.CourseModule;
import com.nology.SpringAPIHalfwayChallenge.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseModuleRepository {

    //defining "fake" database
    private List<CourseModule> moduleDatabase = new ArrayList<>(List.of(
        new CourseModule(
                0,
                "Web Fundamentals & Interactivity",
                new String[] {
                        "Git",
                        "HTML/CSS",
                        "Responsiveness",
                        "DOM Interaction",
                        "Package Management"
                },
                "Individual Project"
        ),
        new CourseModule(
                1,
                "Object Oriented Programming & Testing",
                new String[] {
                        "Programming Concepts",
                        "Classes and Encapsulation",
                        "Test Driven Development",
                        "Unit Testing",
                        "APIs & REST"
                },
                "Individual Project"
        ),
        new CourseModule(
                2,
                "Professional Skills",
                new String[] {
                        "Assertiveness",
                        "Listening",
                        "Failing",
                        "Presentations",
                        "Agile Projects"
                },
                "Interview Practice"
        ),
        new CourseModule(
                3,
                "JavaScript Frameworks",
                new String[] {
                        "React",
                        "Firebase",
                        "NOSQL Database",
                        "Backend as a Service",
                        "Data Services",
                        "Authentication"
                },
                "Client Projects"
        ),
        new CourseModule(
                4,
                "Java & Cloud Computing",
                new String[] {
                        "Java",
                        "Spring",
                        "RESTful APIs",
                        "SQL",
                        "Google Cloud"
                },
                "Client Projects"
        )
    ));

    //return all records from database
    public List<CourseModule> findAll() {
        return moduleDatabase;
    }

    //return selected module if the given id has a match
    public CourseModule findModuleById(int id) throws ResourceNotFoundException {
        CourseModule foundModule = moduleDatabase.stream()
                .filter((module) -> module.getId() == id)
                .findFirst()
                .orElse(null);

        if(foundModule == null) {
            throw new ResourceNotFoundException("Could not find module with id: " + id);
        }

        return foundModule;
    }

    //adding a new given record (module instance) to the database
    public void addModule(CourseModule newModule) {
        moduleDatabase.add(newModule);
    }

    //finding module with matching id and update its record with modified version of module
    public CourseModule updateModuleById(int id, CourseModule newModule) throws ResourceNotFoundException {
        CourseModule existingModule = findModuleById(id);
        int dbIndex = moduleDatabase.indexOf(existingModule);
        moduleDatabase.set(dbIndex, newModule);
        return existingModule;
    }

    //deleting module using given id (throw exception if it doesn't exist)
    public void deleteModuleById(int id) throws ResourceNotFoundException {
        moduleDatabase.remove(findModuleById(id));
    }
}
