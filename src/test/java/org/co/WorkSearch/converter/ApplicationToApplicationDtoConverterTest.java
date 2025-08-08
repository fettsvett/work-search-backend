package org.co.WorkSearch.converter;

import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.model.Application;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationToApplicationDtoConverterTest {
    ApplicationToApplicationDtoConverter converter = new ApplicationToApplicationDtoConverter();

    @Test
    void convert() {
        LocalDate startOfYear = LocalDate.of(2025, 1, 1);
        Instant created = startOfYear.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant updated = LocalDate.of(2025, 1, 2).atStartOfDay().toInstant(ZoneOffset.UTC);

        Application application = Application.builder()
                .id(1L)
                .companyName("Company Name")
                .positionName("Position Name")
                .applicationDate(startOfYear)
                .salary("Salary")
                .website("Website")
                .password("Password")
                .active(true)
                .created(created)
                .updated(updated)
                .build();

        ApplicationDto applicationDto = converter.convert(application);

        assertNotNull(applicationDto);
        assertEquals(1L, applicationDto.getId());
        assertEquals("Company Name", applicationDto.getCompanyName());
        assertEquals("Position Name", applicationDto.getPositionName());
        assertEquals(startOfYear, applicationDto.getApplicationDate());
        assertEquals("Salary", applicationDto.getSalary());
        assertEquals("Website", applicationDto.getWebsite());
        assertEquals("Password", applicationDto.getPassword());
        assertTrue(applicationDto.isActive());
        assertEquals(created, applicationDto.getCreated());
        assertEquals(updated, applicationDto.getUpdated());
    }
}