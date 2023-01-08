package com.zerobase.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zerobase.auth.JWTTokenProvider;
import com.zerobase.user.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JWTTokenProvider getTokenProvider() {
        return new JWTTokenProvider();
    }

     @Bean
     public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
     }
     
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf()
            .ignoringAntMatchers("/h2-console/**")
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/user/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
            .antMatchers("/api/manager/**")
            .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
            .antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/sign-up/**", "/api/**").permitAll()
            .anyRequest().permitAll();
            // .and()
            // .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().sameOrigin(); //h2 database가 iframe 사용하여 발생하는 오류 해결
        return http.build();
     }
}