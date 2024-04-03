package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorRegistrationDTO;
import org.pratice.donemile.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<Donor> findAllDonors(){
        return donorRepository.findAll();
    }

    @Override
    @Transactional
    public Donor updateDonor(Donor donor) {
        //ID 존재여부 확인
        if (donor.getId() == null || !donorRepository.existsById(donor.getId())){
            throw new IllegalArgumentException("기부자 정보를 찾을 수 없습니다.");
        }
        return donorRepository.save(donor);
    }

    @Override
    @Transactional(readOnly = true)
    public Donor findDonorById(Long id) {
        return donorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("기부자 정보를 찾을 수 없습니다.")
        );
    }

    @Override
    @Transactional
    public void deleteDonorById(Long id) {
        if(!donorRepository.existsById(id)){
            throw new IllegalArgumentException("기부자 정보를 찾을 수 없습니다.");
        }
        donorRepository.deleteById(id);
    }

}