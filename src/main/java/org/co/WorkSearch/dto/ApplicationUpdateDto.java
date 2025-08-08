package org.co.WorkSearch.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * The Application Update DTO.
 */
@Value
@Builder
public class ApplicationUpdateDto {
    @Min(1)
    Long id;
    @NotEmpty
    String companyName;
    @NotEmpty
    String positionName;
    @NotNull
    LocalDate applicationDate;
    String salary;
    String website;
    String password;
    boolean active;
}
