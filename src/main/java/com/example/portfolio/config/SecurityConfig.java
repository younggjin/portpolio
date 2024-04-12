package com.example.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login_proc", "/member/mem_write_proc", "/admin/**", "/item/**",
                                "/notice/notice", "/notice/review", "/member/**","/css/**", "/images/**", "/index",
                                "/js/**", "/upload/**", "/thumbnail/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/item/**").hasAnyRole("MEMBER", "ADMIN")
                        .anyRequest().authenticated()
                );
        http
                .formLogin(auth -> auth
                        .loginPage("/member/login")
                        .loginProcessingUrl("/login_proc")
                        .usernameParameter("userid")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        http
                .logout(auth -> auth
                .logoutUrl("/logout")
                );

        return http.build();
    }
}
