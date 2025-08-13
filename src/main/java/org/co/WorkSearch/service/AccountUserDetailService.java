package org.co.WorkSearch.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.co.WorkSearch.model.Account;
import org.co.WorkSearch.model.Authority;
import org.co.WorkSearch.repositories.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUserDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", username);
        Account account = accountRepository.findByUsername(username);
        Collection<GrantedAuthority> authorities = account.getAuthorities().stream()
                .map(Authority::getAuthority)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        log.info("Loaded user: {}", account);
        log.info("Loading user authorities: {}", authorities);

        return CustomUser.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .enabled(account.isActive())
                .authorities(authorities)
                .entity(account)
                .build();
    }


    @Value
    @Builder
    public static class CustomUser implements UserDetails {
        String username;
        String password;
        boolean enabled;
        Collection<? extends GrantedAuthority> authorities;
        Account entity;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getName()).append(" [");
            sb.append("Username=").append(this.username).append(", ");
            sb.append("Password=[PROTECTED], ");
            sb.append("Enabled=").append(this.enabled).append(", ");
            sb.append("Granted Authorities=").append(this.authorities).append("]");
            return sb.toString();
        }
    }
}
