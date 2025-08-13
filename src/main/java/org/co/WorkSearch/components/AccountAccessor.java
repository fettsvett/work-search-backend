package org.co.WorkSearch.components;

import org.co.WorkSearch.model.Account;
import org.co.WorkSearch.service.AccountUserDetailService;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountAccessor {
    public Optional<Account> getCurrentAccount() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(AccountUserDetailService.CustomUser.class::isInstance)
                .map(AccountUserDetailService.CustomUser.class::cast)
                .map(AccountUserDetailService.CustomUser::getEntity)
                .map(Hibernate::unproxy)
                .filter(Account.class::isInstance)
                .map(Account.class::cast);
    }

    public Account getCurrentAccountOrThrow() {
        return getCurrentAccount()
                .orElseThrow(() -> new RuntimeException("Current Account was not found!"));
    }
}
