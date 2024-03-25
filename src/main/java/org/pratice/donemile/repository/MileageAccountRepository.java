package org.pratice.donemile.repository;

import org.pratice.donemile.domain.MileageAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageAccountRepository extends JpaRepository<MileageAccount, Long> {
}
