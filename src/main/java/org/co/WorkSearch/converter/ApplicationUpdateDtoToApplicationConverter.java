package org.co.WorkSearch.converter;

import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationUpdateDtoToApplicationConverter {
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
