package org.co.WorkSearch.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.converter.ApplicationCreationDtoToApplicationConverter;
import org.co.WorkSearch.converter.ApplicationToApplicationDtoConverter;
import org.co.WorkSearch.converter.ApplicationUpdateDtoToApplicationConverter;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;
import org.co.WorkSearch.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationDto getApplicationById(@Min(1) @PathVariable Long id) {
        return applicationService.getApplication(id);
    }

    @PostMapping
    public ApplicationDto createApplication(@Valid @RequestBody ApplicationCreationDto applicationCreationDto) {
        return applicationService.createApplication(applicationCreationDto);
    }

    @PutMapping
    public ApplicationDto updateApplication(@Valid @RequestBody ApplicationUpdateDto applicationUpdateDto) {
        return applicationService.updateApplication(applicationUpdateDto);
    }
}
