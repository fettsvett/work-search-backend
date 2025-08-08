package org.co.WorkSearch.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Application controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    /**
     * Gets a list of all applications.
     * @return Every application.
     */
    @Operation(
            summary = "Get a list of all applications.",
            tags = {
                    "Applications"
            }
    )
    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        return applicationService.getAllApplications();
    }

    /**
     * Gets an individual application by ID.
     * @param id The ID of the application.
     * @return The requested application.
     */
    @Operation(
            summary = "Get an individual Application.",
            tags = {
                    "Applications"
            }
    )
    @GetMapping("/{id}")
    public ApplicationDto getApplicationById(@Min(1) @PathVariable Long id) {
        return applicationService.getApplication(id);
    }

    /**
     * Creates a new Application.
     * @param applicationCreationDto The Application creation DTO.
     * @return A new Application.
     */
    @Operation(
            summary = "Create a new Application.",
            tags = {
                    "Applications"
            }
    )
    @PostMapping
    public ApplicationDto createApplication(@Valid @RequestBody ApplicationCreationDto applicationCreationDto) {
        return applicationService.createApplication(applicationCreationDto);
    }

    /**
     * Updates an existing application.
     * @param applicationUpdateDto The Application update DTO.
     * @return The updated application.
     */
    @Operation(
            summary = "Update an existing Application.",
            tags = {
                    "Applications"
            }
    )
    @PutMapping
    public ApplicationDto updateApplication(@Valid @RequestBody ApplicationUpdateDto applicationUpdateDto) {
        return applicationService.updateApplication(applicationUpdateDto);
    }
}
