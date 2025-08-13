package org.co.WorkSearch.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@Entity
@IdClass(Authority.AuthoritiesId.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    Account account;
    @Id
    @Enumerated(EnumType.STRING)
    AuthorityName authority;

    @FieldNameConstants(onlyExplicitlyIncluded = true, innerTypeName = "Values")
    public enum AuthorityName {
        @FieldNameConstants.Include ROLE_USER,
        @FieldNameConstants.Include ROLE_ADMIN
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthoritiesId implements Serializable {
        Long account;
        AuthorityName authority;
    }
}
