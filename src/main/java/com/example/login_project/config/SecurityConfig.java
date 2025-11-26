package com.example.login_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.login_project.utils.JwtUtil;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 기능 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    // 1. 암호화 도구 (비밀번호를 안전하게 바꾸는 기계) 등록
    // 이 Bean이 등록되어야 UserService에서 사용할 수 있습니다!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. 보안 필터 체인 (실제 경비원 역할)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // (1) CSRF 보호 기능 비활성화 (테스트 원활하게 하기 위해)
                .csrf(AbstractHttpConfigurer::disable)

                // (2) HTTP Basic 인증 비활성화 (로그인 폼 화면 안 뜨게 함)
                .httpBasic(AbstractHttpConfigurer::disable)

                // (3) 폼 로그인 기능 비활성화 (우리는 직접 구현할 거니까)
                .formLogin(AbstractHttpConfigurer::disable)

                // (4) 요청 주소별 권한 설정 (가장 중요!)
                .authorizeHttpRequests(authorize -> authorize
                        // "/signup" 같은 회원가입 URL은 로그인 없이도 들어갈 수 있어야 함
                        .requestMatchers("/signup", "/", "/login", "/error").permitAll()
                        // 그 외 모든 요청은 인증(로그인) 필요함
                        .anyRequest().authenticated()
                )

                // 기존 로그인 필터보다 먼저 JwtFilter를 실행하도록 함
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}