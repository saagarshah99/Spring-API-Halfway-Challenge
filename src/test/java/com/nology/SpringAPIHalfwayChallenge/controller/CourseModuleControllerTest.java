package com.nology.SpringAPIHalfwayChallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.SpringAPIHalfwayChallenge.entity.CourseModule;
import com.nology.SpringAPIHalfwayChallenge.repository.CourseModuleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseModuleController.class)
public class CourseModuleControllerTest {

    //new module to add to database
    private final CourseModule newModule = new CourseModule(
            5,
            "Job Preparation",
            new String[] {
                    "Job Seeking Technique",
                    "Interview Technique",
                    "LinkedIn/CV Coaching",
                    "Portfolio Demo"
            },
            "Final Portfolio Delivery"
    );

    //mocking process of updating existing module record
    private final CourseModule updatedModule = new CourseModule(
            2,
            "Professional Development",
            new String[] {
                    "Assertiveness",
                    "Listening",
                    "Failing",
                    "Presentations",
                    "Agile Projects"
            },
            "Mock Interview"
    );

    //pretend to make API requests and mock the module repo
    @Autowired private MockMvc mockMvc;
    @MockBean private CourseModuleRepository moduleRepo;

    @Test @DisplayName("Index routes should return a list of modules")
    public void indexRouteShouldReturnModuleList() throws Exception {
        mockMvc.perform(get("/modules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andReturn();
    }

    @Test @DisplayName("Show route should return selected module using its id")
    public void showRouteShouldReturnCorrectModule() throws Exception {
        mockMvc.perform(get("/modules/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.title", is("Web Fundamentals & Interactivity")))
                .andReturn();
    }

    @Test @DisplayName("Show route should throw exception because given module id doesn't exist")
    public void showRouteShouldReturnErrorIdNotFound() throws Exception {
        mockMvc.perform(get("/modules/6"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test @DisplayName("Create route should create new module and return success message")
    public void createRouteShouldCreateModuleAndReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/modules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(newModule))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text", is("Successfully created new module")))
                .andReturn();
    }

    @Test @DisplayName("Update route should edit a module with the given id")
    public void updateRouteShouldEditModuleWithGivenId() throws Exception {
        mockMvc.perform(put("/modules/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedModule))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("Successfully updated module with id: 2")))
                .andReturn();
    }

    @Test @DisplayName("Delete route should delete a module with the given id")
    public void deleteRouteShouldDeleteModuleWithGivenId() throws Exception {
        mockMvc.perform(delete("/modules/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("Successfully deleted module with id: 4")))
                .andReturn();
    }

    //parsing given module object to JSON string
    private static String toJson(CourseModule updatedModule) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(updatedModule));
        return new ObjectMapper().writeValueAsString(updatedModule);
    }
}