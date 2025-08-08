package org.co.WorkSearch.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ApplicationCreationDto {
    String companyName;
    String positionName;
    LocalDate applicationDate;
    String salary;
    String website;
    String password;
    boolean active;
}
