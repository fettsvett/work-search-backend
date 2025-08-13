package org.co.WorkSearch.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.dto.account.AccountCreationDto;
import org.co.WorkSearch.dto.account.AccountDto;
import org.co.WorkSearch.dto.account.AccountUpdateDto;
import org.co.WorkSearch.model.Authority;
import org.co.WorkSearch.service.AccountService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Account controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    /**
     * Gets a list of all accounts.
     * @return Every account.
     */
    @Operation(
            summary = "Get a list of all Accounts.",
            tags = {
                    "Accounts"
            }
    )
    @GetMapping
    @Secured(Authority.AuthorityName.Values.ROLE_ADMIN)
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    /**
     * Gets an individual account by ID.
     * @param id The ID of the account.
     * @return The requested account.
     */
    @Operation(
            summary = "Get an individual Account.",
            tags = {
                    "Accounts"
            }
    )
    @GetMapping("/{id}")
    public AccountDto getApplicationById(@Min(1) @PathVariable Long id) {
        return accountService.getAccount(id);
    }

    /**
     * Creates a new account.
     * @param accountCreationDto The account creation DTO.
     * @return A new account.
     */
    @Operation(
            summary = "Create a new Account.",
            tags = {
                    "Accounts"
            }
    )
    @PostMapping
    public AccountDto createAccount(@Valid @RequestBody AccountCreationDto accountCreationDto) {
        return accountService.createAccount(accountCreationDto);
    }

    /**
     * Updates an existing account.
     * @param accountUpdateDto The Account update DTO.
     * @return The updated account.
     */
    @Operation(
            summary = "Update an existing Account.",
            tags = {
                    "Accounts"
            }
    )
    @PutMapping
    public AccountDto updateAccount(@Valid @RequestBody AccountUpdateDto accountUpdateDto) {
        return accountService.updateAccount(accountUpdateDto);
    }
}
