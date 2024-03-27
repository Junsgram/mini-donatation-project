package org.pratice.donemile.service;

import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.repository.DonorRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DonorRepository donorRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(DonorRepository donorRepository, PasswordEncoder passwordEncoder){
        this.donorRepository = donorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Donor donor = donorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username+"을 찾을 수가 없습니다."));

        return new User(donor.getEmail(), donor.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("Role_" + donor.getRole())));
    }
}
