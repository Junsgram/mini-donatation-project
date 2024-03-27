package org.pratice.donemile.service;

import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorRegistrationDTO;

import java.util.List;

public interface DonorService {
    Donor registerNewDonor(DonorRegistrationDTO donorRegistrationDTO);

    Donor updateDonor(Donor donor);

    Donor findDonorById(Long id);

    List<Donor> findAllDonors();

    void deleteDonorById(Long id);
}
