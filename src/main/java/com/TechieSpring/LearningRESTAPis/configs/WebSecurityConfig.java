package com.TechieSpring.LearningRESTAPis.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpsecurity) throws Exception{
        httpsecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/employees/**").permitAll()
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig ->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .formLogin(Customizer.withDefaults());

        return httpsecurity.build();
    }
    @Bean
    UserDetailsService myInMemoryUserDetailsService(){
        UserDetails normalUser = User
                .withUsername("daya")
                .password(passwordEncoder().encode("daya@123"))
                .roles("USER")
                .build();
        UserDetails adminUser= User
                .withUsername("Sanju")
                .password(passwordEncoder().encode("sanju@123")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
