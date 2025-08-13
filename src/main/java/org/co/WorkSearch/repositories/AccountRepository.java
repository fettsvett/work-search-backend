package org.co.WorkSearch.repositories;

import org.co.WorkSearch.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The Application Repository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a.id, a.username, a.password, a.active FROM Account a WHERE a.username = ?1")
    Optional<AccountLogin> loginQuery(String username);
    Account findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    record AccountLogin(long id, String username, String password, boolean active) {}
}
