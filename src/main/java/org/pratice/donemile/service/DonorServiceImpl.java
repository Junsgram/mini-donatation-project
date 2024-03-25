package org.pratice.donemile.service;

import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorRegistrationDTO;
import org.pratice.donemile.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonorServiceImpl implements DonorService{

    private final DonorRepository donorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository, PasswordEncoder passwordEncoder){
        this.donorRepository = donorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Donor registerNewDonor(DonorRegistrationDTO donorRegistrationDTO) {
        if(donorRepository.existsByEmail(donorRegistrationDTO.getEmail())){
            throw new RuntimeException("이미 사용중인 이메일주소입니다.");
        }

        Donor donor = new Donor();
        donor.setEmail(donorRegistrationDTO.getEmail());
        donor.setPassword(passwordEncoder.encode(donorRegistrationDTO.getPassword()));
        donor.setDonorName(donorRegistrationDTO.getDonorName());
        donor.setNickName(donorRegistrationDTO.getNickName());
        donor.setAddress(donorRegistrationDTO.getAddress());
        donor.setBirth(donorRegistrationDTO.getBirth());
        donor.setPhoneNum(donorRegistrationDTO.getPhoneNum());
        donor.setRole(donorRegistrationDTO.getRole());

        return donorRepository.save(donor);

    }

}
