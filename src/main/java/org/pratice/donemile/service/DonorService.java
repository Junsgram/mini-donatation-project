package org.pratice.donemile.service;

import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorRegistrationDTO;

public interface DonorService {
    Donor registerNewDonor(DonorRegistrationDTO donorRegistrationDTO);
}
