package org.co.WorkSearch.controller;

import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationService applicationService;

    @Test
    void getAllApplications() throws Exception {
        when(applicationService.getAllApplications()).thenReturn(List.of(ApplicationDto.builder().id(1L).build()));

        mockMvc.perform(get("/applications"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: 1}]"));
    }

    @Test
    void getApplicationById() throws Exception {
        when(applicationService.getApplication(1L)).thenReturn(ApplicationDto.builder().id(1L).build());

        mockMvc.perform(get("/applications/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1}"));
    }

    @Test
    void createApplication() throws Exception {
        when(applicationService.createApplication(any(ApplicationCreationDto.class)))
                .thenReturn(ApplicationDto.builder().id(1L).build());

        String body = "{\n" +
                "\t\"companyName\": \"Boeing\",\n" +
                "\t\"positionName\": \"Mid-Level Backend Developer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(post("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1}"));
    }

    @Test
    void createApplication_invalidCompanyName_null() throws Exception {
        String body = "{\n" +
                "\t\"companyName\": null,\n" +
                "\t\"positionName\": \"Mid-Level Backend Developer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(post("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplication_invalidCompanyName_blank() throws Exception {
        String body = "{\n" +
                "\t\"companyName\": \"\",\n" +
                "\t\"positionName\": \"Mid-Level Backend Developer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(post("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplication_invalidPositionName_null() throws Exception {
        String body = "{\n" +
                "\t\"companyName\": \"Boeing\",\n" +
                "\t\"positionName\": null,\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(post("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplication_invalidPositionName_blank() throws Exception {
        String body = "{\n" +
                "\t\"companyName\": \"Boeing\",\n" +
                "\t\"positionName\": \"\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(post("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplication_invalidApplicationDate_null() throws Exception {
        String body = "{\n" +
                "\t\"companyName\": \"Boeing\",\n" +
                "\t\"positionName\": \"Mid-Level Backend Developer\",\n" +
                "\t\"applicationDate\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(post("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplication_emptyBody() throws Exception {
        when(applicationService.createApplication(any(ApplicationCreationDto.class)))
                .thenReturn(ApplicationDto.builder().id(1L).build());

        mockMvc.perform(post("/applications").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication() throws Exception {
        when(applicationService.updateApplication(any(ApplicationUpdateDto.class)))
                .thenReturn(ApplicationDto.builder().id(1L).build());

        String body = "{\n" +
                "\t\"id\": 2,\n" +
                "\t\"companyName\": \"Jam City\",\n" +
                "\t\"positionName\": \"Backend Engineer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1}"));
    }

    @Test
    void updateApplication_invalidId_null() throws Exception {
        String body = "{\n" +
                "\t\"id\": null,\n" +
                "\t\"companyName\": \"Jam City\",\n" +
                "\t\"positionName\": \"Backend Engineer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_invalidId_lessThanOne() throws Exception {
        String body = "{\n" +
                "\t\"id\": 0,\n" +
                "\t\"companyName\": \"Jam City\",\n" +
                "\t\"positionName\": \"Backend Engineer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_invalidCompanyName_null() throws Exception {
        String body = "{\n" +
                "\t\"id\": 2,\n" +
                "\t\"companyName\": null,\n" +
                "\t\"positionName\": \"Backend Engineer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_invalidCompanyName_blank() throws Exception {
        String body = "{\n" +
                "\t\"id\": 2,\n" +
                "\t\"companyName\": \"\",\n" +
                "\t\"positionName\": \"Backend Engineer\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_invalidPositionName_null() throws Exception {
        String body = "{\n" +
                "\t\"id\": 2,\n" +
                "\t\"companyName\": \"Jam City\",\n" +
                "\t\"positionName\": null,\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_invalidPositionName_blank() throws Exception {
        String body = "{\n" +
                "\t\"id\": 2,\n" +
                "\t\"companyName\": \"Jam City\",\n" +
                "\t\"positionName\": \"\",\n" +
                "\t\"applicationDate\": \"2025-07-29\",\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_invalidApplicationDate_null() throws Exception {
        String body = "{\n" +
                "\t\"id\": 2,\n" +
                "\t\"companyName\": \"Jam City\",\n" +
                "\t\"positionName\": \"Backend Engineer\",\n" +
                "\t\"applicationDate\": null,\n" +
                "\t\"salary\": \"\",\n" +
                "\t\"website\": \"https://www.jamcity.com/job-openings/\",\n" +
                "\t\"password\": null,\n" +
                "\t\"active\": true\n" +
                "}";

        mockMvc.perform(put("/applications").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateApplication_emptyBody() throws Exception {
        mockMvc.perform(put("/applications").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}