package com.codestep.portfolio1app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class Portfolio1SecurityConfig {
   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable);
       /*http.headers(headers -> headers
    		   .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)); // H2Consoleで必要*/
       http.authorizeHttpRequests(requests -> requests
    		   .anyRequest().permitAll());
       http.formLogin(form -> form
    		   .defaultSuccessUrl("/index"));
       http.logout(LogoutConfigurer::permitAll);
       return http.build();
   }
}
