package org.co.WorkSearch.converter.account;

import org.co.WorkSearch.dto.account.AccountCreationDto;
import org.co.WorkSearch.model.Account;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class to convert {@link AccountCreationDto} to {@link Account} objects.
 */
@Component
public class AccountCreationDtoToApplicationConverter {
    /**
     * Convert from {@link AccountCreationDto} to {@link Account} objects.
     * @param source The {@link AccountCreationDto} object.
     * @return A converted {@link Account} object.
     */
    @NonNull
    public Account convert(@NonNull AccountCreationDto source) {
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return Account.builder()
                .username(source.getUsername())
                .password(passwordEncoder.encode(source.getPassword()))
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .active(source.isActive())
                .build();
    }
}
