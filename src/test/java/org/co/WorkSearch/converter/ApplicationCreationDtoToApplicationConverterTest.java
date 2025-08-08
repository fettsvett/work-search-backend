package org.co.WorkSearch.converter;

import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.model.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationCreationDtoToApplicationConverterTest {
    private final ApplicationCreationDtoToApplicationConverter converter = new ApplicationCreationDtoToApplicationConverter();

    @Test
    void convert() {
        ApplicationCreationDto applicationCreationDto = ApplicationCreationDto.builder()
                .companyName("companyName")
                .positionName("positionName")
                .applicationDate(LocalDate.EPOCH)
                .salary("salary")
                .website("website")
                .password("password")
                .active(true)
                .build();

        Application application = converter.convert(applicationCreationDto);

        assertNotNull(application);
        assertEquals("companyName", application.getCompanyName());
        assertEquals("positionName", application.getPositionName());
        assertEquals(LocalDate.EPOCH, application.getApplicationDate());
        assertEquals("salary", application.getSalary());
        assertEquals("website", application.getWebsite());
        assertEquals("password", application.getPassword());
        assertTrue(application.isActive());
    }
}