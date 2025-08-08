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

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationToApplicationDtoConverter applicationToApplicationDtoConverter;
    private final ApplicationCreationDtoToApplicationConverter applicationCreationDtoToApplicationConverter;
    private final ApplicationUpdateDtoToApplicationConverter applicationUpdateDtoToApplicationConverter;

    @Override
    public List<ApplicationDto> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(applicationToApplicationDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto getApplication(Long id) {
        return applicationRepository.findById(id)
                .map(applicationToApplicationDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Application ID: %s was not found!", id)));
    }

    @Override
    public ApplicationDto createApplication(ApplicationCreationDto applicationCreationDto) {
        Application application = applicationCreationDtoToApplicationConverter.convert(applicationCreationDto);

        return applicationToApplicationDtoConverter.convert(applicationRepository.save(application));
    }

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
