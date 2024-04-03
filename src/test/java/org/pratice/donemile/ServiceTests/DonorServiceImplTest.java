package org.pratice.donemile.ServiceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pratice.donemile.constant.Role;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorRegistrationDTO;
import org.pratice.donemile.repository.DonorRepository;
import org.pratice.donemile.service.DonorServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonorServiceImplTest {

    @Mock
    private DonorRepository donorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DonorServiceImpl donorService;

    //CREATE
    @Test
    public void whenRegisteringNewDonor_thenSuccess(){
        //Given
        DonorRegistrationDTO registrationDTO = new DonorRegistrationDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setDonorName("Gukjin Hong");
        registrationDTO.setNickName("Gukhong");
        registrationDTO.setAddress("123 Main st, ulsan, Korea");
        registrationDTO.setBirth(LocalDate.of(1987,11,3));
        registrationDTO.setPhoneNum("010-1111-1111");
        registrationDTO.setRole(Role.DONOR);

        when(donorRepository.existsByEmail(anyString())).thenReturn(false);
        when(donorRepository.save(any(Donor.class))).thenAnswer(i -> i.getArguments()[0]);
        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");

        //When
        Donor result = donorService.registerNewDonor(registrationDTO);

        //Then
        assertNotNull(result); // 결과 객체가 null이 아닌지 확인
        assertEquals("test@example.com", result.getEmail()); // 이메일이 올바르게 설정되었는지 확인
        assertEquals("encodedPassword", result.getPassword()); // 비밀번호가 올바르게 인코딩되었는지 확인
        assertEquals("Gukjin Hong", result.getDonorName()); // 기부자 이름이 올바르게 설정되었는지 확인
        assertEquals("Gukhong", result.getNickName()); // 닉네임이 올바르게 설정되었는지 확인
        assertEquals("123 Main st, ulsan, Korea", result.getAddress()); // 주소가 올바르게 설정되었는지 확인
        assertEquals(LocalDate.of(1987, 11, 3), result.getBirth()); // 생일이 올바르게 설정되었는지 확인
        assertEquals("010-1111-1111", result.getPhoneNum()); // 전화번호가 올바르게 설정되었는지 확인
        assertEquals(Role.DONOR, result.getRole()); // 역할이 올바르게 설정되었는지 확인

        verify(donorRepository).existsByEmail("test@example.com");
        verify(donorRepository).save(any(Donor.class));

    }

    @Test
    public void whenRegisteringDonorWithExistingEmail_thenThrowException() {
        //Given
        DonorRegistrationDTO registrationDTO = new DonorRegistrationDTO();
        registrationDTO.setEmail("existing@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setDonorName("Existing Donor");
        registrationDTO.setNickName("ExistingNick");
        registrationDTO.setAddress("ExistingAddress");
        registrationDTO.setBirth(LocalDate.of(1987,11,3));
        registrationDTO.setPhoneNum("ExistingPhoneNum");
        registrationDTO.setRole(Role.DONOR);

        // when
        when(donorRepository.existsByEmail(registrationDTO.getEmail())).thenReturn(true);

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            donorService.registerNewDonor(registrationDTO);
        });

        assertEquals("이미 사용중인 이메일주소입니다.", exception.getMessage());
        verify(donorRepository).existsByEmail(registrationDTO.getEmail());
        verify(donorRepository, never()).save(any(Donor.class));
    }


    //READ
    @Test
    public void whenFindAllDonors_thenAllDonorsShouldBeReturned(){
        when(donorRepository.findAll()).thenReturn(Arrays.asList(new Donor(), new Donor()));

        List<Donor> donors = donorService.findAllDonors();

        assertNotNull(donors);
        assertEquals(2, donors.size());
        verify(donorRepository).findAll();
    }


    //UPDATE
    @Test
    void whenUpdateDonor_thenShouldReturnUpdatedDonor() {
        Donor existingDonor = new Donor();
        existingDonor.setId(1L);
        existingDonor.setEmail("test@example.com");
        existingDonor.setPassword("password");
        existingDonor.setDonorName("Gukjin Hong");
        existingDonor.setNickName("Gukhong");
        existingDonor.setAddress("123 Main st, ulsan, Korea");
        existingDonor.setBirth(LocalDate.of(1987,11,3));
        existingDonor.setPhoneNum("010-1111-1111");
        existingDonor.setRole(Role.DONOR);

        when(donorRepository.existsById(anyLong())).thenReturn(true);
        when(donorRepository.save(any(Donor.class))).then(invocation -> invocation.getArgument(0));

        Donor updatedDonor = donorService.updateDonor(existingDonor);

        assertNotNull(updatedDonor);
        verify(donorRepository).save(any(Donor.class));
    }

    //DELETE
    @Test
    void whenDeleteDonorById_thenShouldInvokeDeletion() {
        Long donorId = 1L;
        when(donorRepository.existsById(donorId)).thenReturn(true);

        donorService.deleteDonorById(donorId);

        verify(donorRepository).deleteById(donorId);
    }

}
