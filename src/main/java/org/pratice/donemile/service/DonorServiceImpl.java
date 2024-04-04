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
    public Donor updateDonor(Donor updatedDonor) {
        if (updatedDonor.getId() == null || !donorRepository.existsById(updatedDonor.getId())){
            throw  new IllegalArgumentException("기부자 정보를 찾을 수 없습니다.");
        }

        Donor existingDonor = donorRepository.findById(updatedDonor.getId())
                .orElseThrow(() -> new IllegalArgumentException("기부자 정보를 찾을 수 없습니다."));

        // 비밀번호 변경이 요청된 경우에만 업데이트
        if (updatedDonor.getPassword() != null && !updatedDonor.getPassword().trim().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updatedDonor.getPassword());
            existingDonor.setPassword(encodedPassword);
        }

        // 다른 필드 업데이트. 변경 사항이 있는 필드만 업데이트를 수행
        if (updatedDonor.getEmail() != null && !updatedDonor.getEmail().isEmpty()) {
            existingDonor.setEmail(updatedDonor.getEmail());
        }
        if (updatedDonor.getDonorName() != null && !updatedDonor.getDonorName().isEmpty()) {
            existingDonor.setDonorName(updatedDonor.getDonorName());
        }
        if (updatedDonor.getNickName() != null && !updatedDonor.getNickName().isEmpty()) {
            existingDonor.setNickName(updatedDonor.getNickName());
        }
        if (updatedDonor.getAddress() != null && !updatedDonor.getAddress().isEmpty()) {
            existingDonor.setAddress(updatedDonor.getAddress());
        }
        if (updatedDonor.getBirth() != null) {
            existingDonor.setBirth(updatedDonor.getBirth());
        }
        if (updatedDonor.getPhoneNum() != null && !updatedDonor.getPhoneNum().isEmpty()) {
            existingDonor.setPhoneNum(updatedDonor.getPhoneNum());
        }

        return donorRepository.save(existingDonor);
    }


    @Override
    @Transactional(readOnly = true)
    public Donor findDonorById(Long id) {
        return donorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("기부자 정보를 찾을 수 없습니다.")
        );
    }

    @Override
    public Donor findDonorByEmail(String email){
        return donorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("기부자를 이메일로 찾을 수 없습니다."+ email));
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