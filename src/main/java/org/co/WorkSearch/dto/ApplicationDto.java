package org.co.WorkSearch.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

/**
 * The Application DTO
 */
@Value
@Builder
public class ApplicationDto {
    Long id;
    String companyName;
    String positionName;
    LocalDate applicationDate;
    String salary;
    String website;
    String password;
    boolean active;
    Instant created;
    Instant updated;
}
