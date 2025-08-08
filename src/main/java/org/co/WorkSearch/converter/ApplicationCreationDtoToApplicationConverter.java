package org.co.WorkSearch.converter;

import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.model.Application;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCreationDtoToApplicationConverter {
    /**
     * Convert from {@link ApplicationCreationDto} to {@link Application} objects.
     * @param source The {@link ApplicationCreationDto} object.
     * @return A converted {@link Application} object.
     */
    @NonNull
    public Application convert(@NonNull ApplicationCreationDto source) {
        return Application.builder()
                .companyName(source.getCompanyName())
                .positionName(source.getPositionName())
                .salary(source.getSalary())
                .applicationDate(source.getApplicationDate())
                .website(source.getWebsite())
                .salary(source.getSalary())
                .password(source.getPassword())
                .active(source.isActive())
                .build();
    }
}
