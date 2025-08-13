package org.co.WorkSearch.dto.account;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class AccountDto {
    Long id;
    String username;
    String firstName;
    String lastName;
    String email;
    boolean active;
    Instant created;
    Instant updated;
}
