package org.co.WorkSearch.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    /**
     * Gets a list of all applications.
     * @return Every application.
     */
    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        return applicationService.getAllApplications();
    }

    /**
     * Gets an individual application by ID.
     * @param id The ID of the application.
     * @return The requested application.
     */
    @GetMapping("/{id}")
    public ApplicationDto getApplicationById(@Min(1) @PathVariable Long id) {
        return applicationService.getApplication(id);
    }

    /**
     * Creates a new Application.
     * @param applicationCreationDto The Application creation DTO.
     * @return A new Application.
     */
    @PostMapping
    public ApplicationDto createApplication(@Valid @RequestBody ApplicationCreationDto applicationCreationDto) {
        return applicationService.createApplication(applicationCreationDto);
    }

    /**
     * Updates an existing application.
     * @param applicationUpdateDto The Application update DTO.
     * @return The updated application.
     */
    @PutMapping
    public ApplicationDto updateApplication(@Valid @RequestBody ApplicationUpdateDto applicationUpdateDto) {
        return applicationService.updateApplication(applicationUpdateDto);
    }
}
