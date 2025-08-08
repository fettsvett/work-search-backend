package org.co.WorkSearch.service;

import jakarta.persistence.EntityNotFoundException;
import org.co.WorkSearch.converter.ApplicationCreationDtoToApplicationConverter;
import org.co.WorkSearch.converter.ApplicationToApplicationDtoConverter;
import org.co.WorkSearch.converter.ApplicationUpdateDtoToApplicationConverter;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;
import org.co.WorkSearch.repositories.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private ApplicationToApplicationDtoConverter applicationToApplicationDtoConverter;
    @Mock
    private ApplicationCreationDtoToApplicationConverter applicationCreationDtoToApplicationConverter;
    @Mock
    private ApplicationUpdateDtoToApplicationConverter applicationUpdateDtoToApplicationConverter;
    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    void getAllApplications() {
        when(applicationRepository.findAll()).thenReturn(Arrays.asList(Application.builder().build(), Application.builder().build()));
        when(applicationToApplicationDtoConverter.convert(any(Application.class))).thenReturn(ApplicationDto.builder().build());

        List<ApplicationDto> result = applicationService.getAllApplications();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void createApplication() {
        Application application = Application.builder().build();

        when(applicationCreationDtoToApplicationConverter.convert(any(ApplicationCreationDto.class))).thenReturn(application);
        when(applicationToApplicationDtoConverter.convert(any(Application.class))).thenReturn(ApplicationDto.builder().build());
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        ApplicationDto result = applicationService.createApplication(ApplicationCreationDto.builder().build());

        assertNotNull(result);
        assertEquals(ApplicationDto.builder().build(), result);
    }

    @Test
    void updateApplication() {
        Application application = Application.builder().build();
        when(applicationUpdateDtoToApplicationConverter.convert(any(Application.class), any(ApplicationUpdateDto.class))).thenReturn(application);
        when(applicationToApplicationDtoConverter.convert(any(Application.class))).thenReturn(ApplicationDto.builder().build());
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        ApplicationDto result = applicationService.updateApplication(ApplicationUpdateDto.builder().id(1L).build());

        assertNotNull(result);
        assertEquals(ApplicationDto.builder().build(), result);

    }

    @Test
    void updateApplication_entityNotFound() {
        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> applicationService.updateApplication(ApplicationUpdateDto.builder().id(1L).build()));
    }
}