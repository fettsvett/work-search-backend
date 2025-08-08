package org.co.WorkSearch.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String companyName;
    @Column(nullable = false)
    String positionName;
    @Column(nullable = false)
    LocalDate applicationDate;
    String salary;
    String website;
    String password;
    @Column(nullable = false)
    boolean active;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    Instant created;
    @LastModifiedDate
    Instant updated;
}
