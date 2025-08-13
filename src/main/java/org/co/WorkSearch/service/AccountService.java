package org.co.WorkSearch.service;

import org.co.WorkSearch.dto.account.AccountCreationDto;
import org.co.WorkSearch.dto.account.AccountDto;
import org.co.WorkSearch.dto.account.AccountUpdateDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    AccountDto getAccount(Long id);

    AccountDto createAccount(AccountCreationDto accountCreationDto);

    AccountDto updateAccount(AccountUpdateDto accountUpdateDto);
}
