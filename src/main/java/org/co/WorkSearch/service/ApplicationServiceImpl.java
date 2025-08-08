package org.co.WorkSearch.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.converter.ApplicationCreationDtoToApplicationConverter;
import org.co.WorkSearch.converter.ApplicationToApplicationDtoConverter;
import org.co.WorkSearch.converter.ApplicationUpdateDtoToApplicationConverter;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;
import org.co.WorkSearch.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for manipulating Application entities.
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationToApplicationDtoConverter applicationToApplicationDtoConverter;
    private final ApplicationCreationDtoToApplicationConverter applicationCreationDtoToApplicationConverter;
    private final ApplicationUpdateDtoToApplicationConverter applicationUpdateDtoToApplicationConverter;

    /**
     * Gets a list of all applications.
     * @return A list of all of every application.
     */
    @Override
    public List<ApplicationDto> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(applicationToApplicationDtoConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Gets a single application by ID.
     * @param id The application ID.
     * @throws EntityNotFoundException thrown when the requested application does not exist.
     * @return The requested application
     */
    @Override
    public ApplicationDto getApplication(Long id) {
        return applicationRepository.findById(id)
                .map(applicationToApplicationDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Application ID: %s was not found!", id)));
    }

    /**
     * Creates a new application.
     * @param applicationCreationDto The application creation DTO.
     * @return A new and persisted application.
     */
    @Override
    public ApplicationDto createApplication(ApplicationCreationDto applicationCreationDto) {
        Application application = applicationCreationDtoToApplicationConverter.convert(applicationCreationDto);

        return applicationToApplicationDtoConverter.convert(applicationRepository.save(application));
    }

    /**
     * Updates an existing application.
     * @param applicationUpdateDto The application update DTO.
     * @throws EntityNotFoundException thrown when the requested application does not exist.
     * @return An updated and persisted application
     */
    @Override
    public ApplicationDto updateApplication(ApplicationUpdateDto applicationUpdateDto) {
        Application application = applicationRepository.findById(applicationUpdateDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Application ID: %d was not found!", applicationUpdateDto.getId())));

        Application converted = applicationUpdateDtoToApplicationConverter.convert(application, applicationUpdateDto);

        return applicationToApplicationDtoConverter.convert(applicationRepository.save(converted));
    }
}
