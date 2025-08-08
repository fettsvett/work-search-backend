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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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

        mockMvc.perform(post("/applications").content("{}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1}"));
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

        mockMvc.perform(put("/applications").content("{}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1}"));
    }

    @Test
    void updateApplication_emptyBody() throws Exception {
        when(applicationService.updateApplication(any(ApplicationUpdateDto.class)))
                .thenReturn(ApplicationDto.builder().id(1L).build());

        mockMvc.perform(put("/applications").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}