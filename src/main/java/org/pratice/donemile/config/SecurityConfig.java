package org.pratice.donemile.config;

import lombok.extern.log4j.Log4j2;
import org.pratice.donemile.handler.DonorLoginSuccessHandler;
import org.pratice.donemile.service.DonorOAuth2UserDetailsService;
import org.pratice.donemile.service.DonorUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

@Configuration
@EnableWebSecurity
@Log4j2
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final DonorUserDetailsService donorUserDetailsService;
    private final DonorOAuth2UserDetailsService donorOAuth2UserDetailsService;
    private final PasswordEncoder passwordEncoder;
    public SecurityConfig(@Lazy DonorUserDetailsService donorUserDetailsService,
                          DonorOAuth2UserDetailsService donorOAuth2UserDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.donorUserDetailsService = donorUserDetailsService;
        this.donorOAuth2UserDetailsService = donorOAuth2UserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/donor/**","/login","/webjars/**" ,"/**","/error","/","/css/**", "/js/**", "/img/**", "/vendor/**", "/signup").permitAll()
                .requestMatchers("/episode/**").hasAnyRole("ADMIN","DONOR")
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form
//                        .loginPage("/login")  //로그인 페이지 경로
                        .loginProcessingUrl("/loginProc") //로그인 처리 경로
                        .usernameParameter("email") //로그인 폼의 'username'파라미터 명을 'email'로 설정
                        .defaultSuccessUrl("/", true) //로그인 성공 후 리다이렉션할 URL
                        .failureUrl("/login?error=true") //로그인 실패 시 이동할 URL
                        .permitAll() // 모든 사용자 접근 허용
        );
        http.csrf(csrf ->
                csrf.ignoringRequestMatchers("/**") //회원가입 경로에 대한 CSRF검증 비활성화
        );
        http.logout(logout -> logout
                .logoutUrl("/logout") //로그아웃 처리 경로
                .logoutSuccessUrl("/login").permitAll()  // 로그아웃 성공 후 이동페이지
                .permitAll() //모든 사용자 접근 허용
        );

        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                                .userService(donorOAuth2UserDetailsService)// OAuth2 사용자 서비스 설정
                )
                // 필요한 경우 로그인 후 리디렉션 URL 등 추가 설정 가능
                .successHandler(donorLoginSuccessHandler())
                .failureUrl("/login?error=true")
        );
        http.rememberMe(rememberMe->rememberMe
                .tokenValiditySeconds((int) Duration.ofDays(7).getSeconds()) // Duration을 초 단위로 변환
                .userDetailsService(donorUserDetailsService));

        http.userDetailsService(donorUserDetailsService); //UserDetailsService설정

        return http.build();
    }

    @Bean
    public DonorLoginSuccessHandler donorLoginSuccessHandler() {
        // 생성자에서 PasswordEncoder 사용
        return new DonorLoginSuccessHandler(passwordEncoder);
    }
}
