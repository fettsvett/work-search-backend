package org.co.WorkSearch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.co.WorkSearch.repositories.AccountRepository;
import org.co.WorkSearch.repositories.AuthorityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUserDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Logging in username: {}", username);
        AccountRepository.AccountLogin account = accountRepository.loginQuery(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s was not found!", username)));

        Collection<GrantedAuthority> authorities = authorityRepository.loginQuery(account.id()).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        log.debug("Loaded user record: {}", account);
        log.debug("Loading user authorities: {}", authorities);

        return User.builder()
                .username(account.username())
                .password(account.password())
                .disabled(!account.active())
                .authorities(authorities)
                .build();
    }
}
