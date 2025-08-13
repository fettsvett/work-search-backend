package org.co.WorkSearch.repositories;

import org.co.WorkSearch.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Application Repository
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
