package org.co.WorkSearch.components;

import lombok.RequiredArgsConstructor;
import org.co.WorkSearch.model.Account;
import org.co.WorkSearch.repositories.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountAccessor {
    private final AccountRepository accountRepository;
    public Optional<Account> getCurrentAccount() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .map(User::getUsername)
                .map(accountRepository::findByUsername);
    }

    public Account getCurrentAccountOrThrow() {
        return getCurrentAccount()
                .orElseThrow(() -> new RuntimeException("Current Account was not found!"));
    }
}
