package org.co.WorkSearch.repositories;

import org.co.WorkSearch.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Application Repository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
