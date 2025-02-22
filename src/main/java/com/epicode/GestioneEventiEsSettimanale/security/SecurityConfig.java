package com.epicode.GestioneEventiEsSettimanale.security;


import com.epicode.GestioneEventiEsSettimanale.security.jwt.AuthEntryPoint;
import com.epicode.GestioneEventiEsSettimanale.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl detailsImpl;

    @Autowired
    AuthEntryPoint gestoreNOAuthorization;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(detailsImpl);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(gestoreNOAuthorization))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/utenti/**").permitAll() // Permetti a tutti di accedere alle API degli utenti
                                .requestMatchers(HttpMethod.POST, "/api/eventi").hasRole("ORGANIZZATORE")
                                .requestMatchers(HttpMethod.PUT, "/api/eventi/**").hasRole("ORGANIZZATORE")
                                .requestMatchers(HttpMethod.DELETE, "/api/eventi/**").hasRole("ORGANIZZATORE")
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}

