package org.co.WorkSearch.repositories;

import org.co.WorkSearch.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Application Repository
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("SELECT a.authority FROM Authority a WHERE a.account.id = ?1")
    List<String> loginQuery(Long accountId);
}
