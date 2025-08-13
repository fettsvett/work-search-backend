package org.co.WorkSearch.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class AccountCreationDto {
    @NotBlank
    String username;
    @NotBlank
    @Length(min = 8)
    String password;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Email
    String email;
    boolean active;
}
