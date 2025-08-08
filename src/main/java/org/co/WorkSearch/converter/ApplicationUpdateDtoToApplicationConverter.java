package org.co.WorkSearch.converter;

import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Class to merge {@link ApplicationUpdateDto} objects into {@link Application} entities.
 */
@Component
@RequiredArgsConstructor
public class ApplicationUpdateDtoToApplicationConverter {
    /**
     * Merge {@link ApplicationUpdateDto} into {@link Application} entity.
     * @param application The {@link Application} entity.
     * @param source The {@link ApplicationUpdateDto} object.
     * @return A merged {@link Application} object with updated properties.
     */
    @NonNull
    public Application convert(@NonNull Application application, @NonNull ApplicationUpdateDto source) {
        application.setCompanyName(source.getCompanyName());
        application.setPositionName(source.getPositionName());
        application.setApplicationDate(source.getApplicationDate());
        application.setSalary(source.getSalary());
        application.setWebsite(source.getWebsite());
        application.setPassword(source.getPassword());
        application.setActive(source.isActive());

        return application;
    }
}
