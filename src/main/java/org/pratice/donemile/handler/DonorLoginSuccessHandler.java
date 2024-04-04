package org.pratice.donemile.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.pratice.donemile.dto.DonorAuthDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
public class DonorLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public DonorLoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("onAuthenticationSuccess");
        DonorAuthDTO authDonor = (DonorAuthDTO)authentication.getPrincipal();

        boolean fromSocial = authDonor.isFromSocial();

        boolean passwordResult = passwordEncoder.matches("0000", authDonor.getPassword());

        if(fromSocial && passwordResult) {
            redirectStrategy.sendRedirect(request,response, "/");
        }


    }
}
