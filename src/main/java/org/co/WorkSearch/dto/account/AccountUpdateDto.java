package org.co.WorkSearch.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountUpdateDto {
    @Min(1)
    Long id;
    @NotBlank
    String username;
    @NotBlank
    @Min(8)
    String password;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Email
    String email;
    boolean active;
}
