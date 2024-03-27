package org.pratice.donemile.repository;

import org.pratice.donemile.domain.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    boolean existsByEmail(String email);

    Optional<Donor> findByEmail(String email);
}
