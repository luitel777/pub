package com.luitel.pub.register;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(
        (requests) -> requests
            .requestMatchers("/style/**", "/uploads/**", "/register")
            .permitAll()
            .anyRequest()
            .authenticated())
        .formLogin(
            (form) -> form
                .loginPage("/login")
                .permitAll())
        .logout(
            (logout) -> logout.permitAll());
    return http.build();
  }

  @Autowired
  private DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT username, password, true FROM pubbers WHERE username=?")
        .authoritiesByUsernameQuery("SELECT username, 'USER' FROM pubbers WHERE username=?");
    ;
  }
}
