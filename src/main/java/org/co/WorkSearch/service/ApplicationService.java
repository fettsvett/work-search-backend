package org.co.WorkSearch.service;

import org.co.WorkSearch.dto.ApplicationCreationDto;
import org.co.WorkSearch.dto.ApplicationDto;
import org.co.WorkSearch.dto.ApplicationUpdateDto;
import org.co.WorkSearch.model.Application;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDto> getAllApplications();

    ApplicationDto getApplication(Long id);

    ApplicationDto createApplication(ApplicationCreationDto applicationCreationDto);

    ApplicationDto updateApplication(ApplicationUpdateDto applicationUpdateDto);
}
