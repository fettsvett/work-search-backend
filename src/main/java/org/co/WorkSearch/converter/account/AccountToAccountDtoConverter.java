package org.co.WorkSearch.converter.account;

import org.co.WorkSearch.dto.account.AccountDto;
import org.co.WorkSearch.model.Account;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Class to convert {@link Account} entities into {@link AccountDto} objects.
 */
@Component
public class AccountToAccountDtoConverter {
    /**
     * Convert an {@link Account} entity into an {@link AccountDto} object.
     * @param source The {@link Account} entity.
     * @return A converted {@link AccountDto} object.
     */
    @NonNull
    public AccountDto convert(@NonNull Account source) {
        return AccountDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .active(source.isActive())
                .created(source.getCreated())
                .updated(source.getUpdated())
                .build();
    }
}
