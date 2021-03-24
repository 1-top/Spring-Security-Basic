package com.harrybro.security.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin(config -> {
                    config.loginPage("/login")
//                            .successForwardUrl("/") // RequestCache 로 redirect 되는 것이 더 좋을 듯.
                            .failureUrl("/login?error=true");
                })
                .authorizeRequests(config -> {
                    config.antMatchers("/login")
                            .permitAll() // login page 는 모두 허용하는데
                            .antMatchers("/")
                            .authenticated(); // home page 는 인증을 받고 와라.
                });
    }

    @Bean
    UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("user1").password(passwordEncoder().encode("1234")).roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, admin);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
