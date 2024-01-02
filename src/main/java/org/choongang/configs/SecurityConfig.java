package org.choongang.configs;

import org.choongang.member.service.LoginFailureHandler;
import org.choongang.member.service.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //바뀔수 있는 부분은 우리가 알려줘야함.
        /*인증 설정S - 로그인*/
        http.formLogin(f -> {
            f.loginPage("/member/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    //.defaultSuccessUrl("/")//성공시 이동
                    .successHandler(new LoginSuccessHandler())
                    //.failureUrl("/member/login?error=true");//실패시 이동
                    .failureHandler(new LoginFailureHandler());

        });//도메인 특허 관련 기술
        /*인증 설정 E = 로그인*/
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
