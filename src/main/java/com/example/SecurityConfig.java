package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/", "/webjars/**", "/index.html").permitAll()  // Permit access to "/", webjars, and index.html
                            .anyRequest().authenticated()  // Authenticate all other requests
                    )
                    .exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				    )
                    .csrf((csrf) -> csrf
                            .csrfTokenRepository(new CookieCsrfTokenRepository())
                            .ignoringRequestMatchers("/logout")
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/")  // Redirect to home after logout
                            .deleteCookies("JSESSIONID")  // Delete session cookie on logout
                            .permitAll()  // Allow everyone to access the logout endpoint
                    )
                    .oauth2Login(Customizer.withDefaults());  // Enable OAuth2 login

            return http.build();
        }

    }
