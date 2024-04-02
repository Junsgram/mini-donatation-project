package org.pratice.donemile.config;


import org.pratice.donemile.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(@Lazy CustomUserDetailsService customUserDetailsService ) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf ->
                        csrf.ignoringRequestMatchers("/**") //회원가입 경로에 대한 CSRF검증 비활성화
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login","/webjars/**" ,"/**","/error","/","/css/**", "/js/**", "/img/**", "/vendor/**", "/signup").permitAll()
                        .requestMatchers("/episode/**").hasAnyRole("ADMIN","DONOR")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
//                        .loginPage("/login")  //로그인 페이지 경로
                        .loginProcessingUrl("/loginProc") //로그인 처리 경로
                        .usernameParameter("email") //로그인 폼의 'username'파라미터 명을 'email'로 설정
                        .defaultSuccessUrl("/", true) //로그인 성공 후 리다이렉션할 URL
                        .failureUrl("/login?error=true") //로그인 실패 시 이동할 URL
                        .permitAll() // 모든 사용자 접근 허용
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") //로그아웃 처리 경로
                        .logoutSuccessUrl("/login").permitAll()  // 로그아웃 성공 후 이동페이지
                        .permitAll() //모든 사용자 접근 허용
                )
                .userDetailsService(customUserDetailsService); //UserDetailsService설정

        return http.build();
    }


}