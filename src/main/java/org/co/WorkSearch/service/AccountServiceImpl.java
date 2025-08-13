package org.co.WorkSearch.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.co.WorkSearch.converter.account.AccountCreationDtoToApplicationConverter;
import org.co.WorkSearch.converter.account.AccountToAccountDtoConverter;
import org.co.WorkSearch.converter.account.AccountUpdateDtoToApplicationConverter;
import org.co.WorkSearch.dto.account.AccountCreationDto;
import org.co.WorkSearch.dto.account.AccountDto;
import org.co.WorkSearch.dto.account.AccountUpdateDto;
import org.co.WorkSearch.model.Account;
import org.co.WorkSearch.model.Authority;
import org.co.WorkSearch.repositories.AccountRepository;
import org.co.WorkSearch.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for manipulating Account entities.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final AccountToAccountDtoConverter accountToAccountDtoConverter;
    private final AccountCreationDtoToApplicationConverter accountCreationDtoToApplicationConverter;
    private final AccountUpdateDtoToApplicationConverter accountUpdateDtoToApplicationConverter;

    /**
     * Gets a list of all accounts.
     * @return A list of all of every account.
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountToAccountDtoConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * Gets a single account by ID.
     * @param id The account ID.
     * @throws EntityNotFoundException thrown when the requested account does not exist.
     * @return The requested account
     */
    @Override
    public AccountDto getAccount(Long id) {
        return accountRepository.findById(id)
                .map(accountToAccountDtoConverter::convert)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account ID: %d was not found!", id)));
    }

    /**
     * Creates a new account.
     * @param accountCreationDto The account creation DTO.
     * @return A new and persisted account.
     */
    @Override
    public AccountDto createAccount(AccountCreationDto accountCreationDto) {
        if (accountRepository.existsByUsername(accountCreationDto.getUsername())) {
            throw new EntityExistsException(String.format("Account username: %s already exists!", accountCreationDto.getUsername()));
        }else if (accountRepository.existsByEmail(accountCreationDto.getEmail())) {
            throw new EntityExistsException(String.format("Account email: %s already exists!", accountCreationDto.getEmail()));
        }

        Account account = accountCreationDtoToApplicationConverter.convert(accountCreationDto);

        log.debug("Creating account: {}", account);

        Account saved = accountRepository.save(account);
        // Create ROLE_USER authority by default.
        Authority userAuthority = Authority.builder()
                .account(account)
                .authority(Authority.AuthorityName.ROLE_USER)
                .build();
        // Save the new authority
        authorityRepository.save(userAuthority);

        return accountToAccountDtoConverter.convert(saved);
    }

    /**
     * Updates an existing account.
     * @param accountUpdateDto The account update DTO.
     * @throws EntityNotFoundException thrown when the requested account does not exist.
     * @return An updated and persisted account.
     */
    @Override
    public AccountDto updateAccount(AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Account ID: %d was not found!", accountUpdateDto.getId())));

        Account converted = accountUpdateDtoToApplicationConverter.convert(account, accountUpdateDto);

        log.debug("Updating account: {}", converted);

        return accountToAccountDtoConverter.convert(accountRepository.save(converted));
    }
}
