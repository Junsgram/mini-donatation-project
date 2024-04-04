package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorAuthDTO;
import org.pratice.donemile.repository.DonorRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class DonorUserDetailsService implements UserDetailsService {

    private final DonorRepository donorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Donor> result = donorRepository.findByEmail(username, false);

        log.debug("Trying to find donor with email {}: {}", username, result.isPresent() ? "found" : "not found");


        if(result.isEmpty()){

            log.warn("Donor with email {} not found or not from social login", username);

            throw new UsernameNotFoundException("Check User Email or from social");
        }

        Donor donor = result.get();

        DonorAuthDTO donorAuth = new DonorAuthDTO(
                donor.getEmail(),
                donor.getPassword(),
                donor.isFromSocial(),
                donor.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );

        donorAuth.setName(donor.getDonorName());
        donorAuth.setFromSocial(donor.isFromSocial());

        log.debug("DonorAuthDTO created: {}", donorAuth);

        return donorAuth;
    }
}
