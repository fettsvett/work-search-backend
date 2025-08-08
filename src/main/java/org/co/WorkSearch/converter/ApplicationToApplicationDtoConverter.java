package org.co.WorkSearch.converter;

import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.model.Application;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ApplicationToApplicationDtoConverter {
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
