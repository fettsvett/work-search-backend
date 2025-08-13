package org.co.WorkSearch.converter.account;

import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.dto.account.AccountUpdateDto;
import org.co.WorkSearch.dto.application.ApplicationUpdateDto;
import org.co.WorkSearch.model.Account;
import org.co.WorkSearch.model.Application;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Class to merge {@link ApplicationUpdateDto} objects into {@link Application} entities.
 */
@Component
@RequiredArgsConstructor
public class AccountUpdateDtoToApplicationConverter {
    /**
     * Merge {@link ApplicationUpdateDto} into {@link Application} entity.
     * @param account The {@link Application} entity.
     * @param source The {@link ApplicationUpdateDto} object.
     * @return A merged {@link Application} object with updated properties.
     */
    @NonNull
    public Account convert(@NonNull Account account, @NonNull AccountUpdateDto source) {
        account.setUsername(source.getUsername());
        account.setPassword(source.getPassword());
        account.setFirstName(source.getFirstName());
        account.setLastName(source.getLastName());
        account.setEmail(source.getEmail());
        account.setActive(source.isActive());

        return account;
    }
}
