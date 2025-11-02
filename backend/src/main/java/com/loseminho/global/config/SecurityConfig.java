package com.loseminho.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정
 * TODO: 추후 JWT 인증 추가 필요
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 개발 단계에서는 비활성화
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // 공개 API
                        .requestMatchers("/", "/api/v1/health", "/api/v1/ping").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll() // 인증 관련
                        .requestMatchers("/api/v1/public/**").permitAll() // 공개 API
                        
                        // 개발 도구
                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger
                        .requestMatchers("/actuator/**").permitAll() // Spring Actuator
                        
                        // 나머지는 인증 필요
                        .anyRequest().authenticated()
                );

        // H2 콘솔 사용을 위한 설정
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
