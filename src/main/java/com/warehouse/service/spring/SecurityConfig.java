package com.warehouse.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.warehouse")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthProviderImpl authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/window/**")
               .authenticated()
               .and()
               .authorizeRequests()
               .antMatchers("/login")
               .anonymous()
               .and()
               .formLogin()
               .loginPage("/login")
               .loginProcessingUrl("/login_check")
               .usernameParameter("login")
               .passwordParameter("password")
               .defaultSuccessUrl("/window")
               .and()
               .exceptionHandling()
               .accessDeniedPage("/window")
               .and()
               .logout();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
