package org.co.WorkSearch.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * The application Creation DTO.
 */
@Value
@Builder
public class ApplicationCreationDto {
    @NotNull
    @NotEmpty
    String companyName;
    @NotNull
    @NotEmpty
    String positionName;
    @NotNull
    LocalDate applicationDate;
    String salary;
    String website;
    String password;
    boolean active;
}
