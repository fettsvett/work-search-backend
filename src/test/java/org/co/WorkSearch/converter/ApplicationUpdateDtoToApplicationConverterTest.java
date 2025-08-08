package org.co.WorkSearch.converter;

import jakarta.persistence.EntityNotFoundException;
import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;
import org.co.WorkSearch.repositories.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationUpdateDtoToApplicationConverterTest {
    private static final LocalDate START_OF_YEAR = LocalDate.of(2025, 1, 1);
    private static final ApplicationUpdateDto APPLICATION_UPDATE_DTO = ApplicationUpdateDto.builder()
            .id(1L)
                .companyName("companyName")
                .positionName("positionName")
                .applicationDate(START_OF_YEAR)
                .salary("salary")
                .website("website")
                .password("password")
                .active(true)
                .build();

    @InjectMocks
    private ApplicationUpdateDtoToApplicationConverter converter;

    @Test
    void convert() {
        Instant today = LocalDate.of(2025, 8, 5).atStartOfDay().toInstant(ZoneOffset.UTC);

        Application application = converter.convert(
                Application.builder().id(1L).created(Instant.EPOCH).updated(today).build(),
                APPLICATION_UPDATE_DTO);

        assertEquals(1L, application.getId());
        assertEquals("companyName", application.getCompanyName());
        assertEquals("positionName", application.getPositionName());
        assertEquals(START_OF_YEAR, application.getApplicationDate());
        assertEquals("salary", application.getSalary());
        assertEquals("website", application.getWebsite());
        assertEquals("password", application.getPassword());
        assertTrue(application.isActive());
        assertEquals(Instant.EPOCH, application.getCreated());
        assertEquals(today, application.getUpdated());
    }
}