package org.co.WorkSearch.converter;

import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.model.Application;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Class to convert {@link Application} entities into {@link ApplicationDto} objects.
 */
@Component
public class ApplicationToApplicationDtoConverter {
    /**
     * Convert an {@link Application} entity into an {@link ApplicationDto} object.
     * @param source The {@link Application} entity.
     * @return A converted {@link ApplicationDto} object.
     */
    @NonNull
    public ApplicationDto convert(@NonNull Application source) {
        return ApplicationDto.builder()
                .id(source.getId())
                .companyName(source.getCompanyName())
                .positionName(source.getPositionName())
                .applicationDate(source.getApplicationDate())
                .salary(source.getSalary())
                .website(source.getWebsite())
                .password(source.getPassword())
                .active(source.isActive())
                .created(source.getCreated())
                .updated(source.getUpdated())
                .build();
    }
}
