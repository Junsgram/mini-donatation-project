package org.pratice.donemile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pratice.donemile.config.SecurityConfig;
import org.pratice.donemile.constant.Role;
import org.pratice.donemile.domain.Donor;
import org.pratice.donemile.dto.DonorAuthDTO;
import org.pratice.donemile.repository.DonorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class DonorOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final DonorRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("clientName: "+ clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);

        oAuth2User.getAttributes().forEach((k,v)->{
            log.info(k+":"+v);
        });

        String email = null;

        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
        }

        log.info("Email: "+ email);

        Donor donor = saveSocialDonor(email);

        DonorAuthDTO donorAuth = new DonorAuthDTO(
                donor.getEmail(),
                donor.getPassword(),
                true,
                donor.getRoleSet().stream().map(
                                role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        donorAuth.setName(donor.getDonorName());

        return donorAuth;
    }

    private Donor saveSocialDonor(String email){

        //기존에 동일한 이메일로 가입한 회원이 있다면 조회만
        Optional<Donor> result = repository.findByEmail(email, true);

        if(result.isPresent()){
            return result.get();
        }

        //기존에 가입된 동일한 이메일이 없다면 회원 추가하고 패스워드는 0000, donorName은 이메일 주소로
        Donor donor = Donor.builder().email(email)
                .donorName(email)
                .password(passwordEncoder.encode("0000"))
                .fromSocial(true)
                .role(Role.OAUTH2_DONOR)
                .build();

        donor.addRole(Role.DONOR);

        repository.save(donor);

        return donor;
    }
}
