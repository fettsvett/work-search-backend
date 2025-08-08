package org.co.WorkSearch.service;

import jakarta.persistence.EntityNotFoundException;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;

import java.util.List;

/**
 * Service for manipulating Application entities.
 */
public interface ApplicationService {
    /**
     * Gets a list of all applications.
     * @return A list of all of every application.
     */
    List<ApplicationDto> getAllApplications();

    /**
     * Gets a single application by ID.
     * @param id The application ID.
     * @throws EntityNotFoundException thrown when the requested application does not exist.
     * @return The requested application
     */
    ApplicationDto getApplication(Long id);

    /**
     * Creates a new application.
     * @param applicationCreationDto The application creation DTO.
     * @return A new and persisted application.
     */
    ApplicationDto createApplication(ApplicationCreationDto applicationCreationDto);

    /**
     * Updates an existing application.
     * @param applicationUpdateDto The application update DTO.
     * @throws EntityNotFoundException thrown when the requested application does not exist.
     * @return An updated and persisted application
     */
    ApplicationDto updateApplication(ApplicationUpdateDto applicationUpdateDto);
}
