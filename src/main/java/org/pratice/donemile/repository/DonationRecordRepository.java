package org.pratice.donemile.repository;

import org.pratice.donemile.domain.DonationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRecordRepository extends JpaRepository<DonationRecord, Long> {
}
