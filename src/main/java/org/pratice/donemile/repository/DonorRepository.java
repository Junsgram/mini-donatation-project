package org.pratice.donemile.repository;

import org.pratice.donemile.domain.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    boolean existsByEmail(String email);
}
