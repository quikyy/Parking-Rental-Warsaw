package com.quikyy.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        httpSecurity
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .and()
                .antMatcher("/new-reservation").formLogin();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
