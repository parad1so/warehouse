package com.warehouse.service.spring;

import com.warehouse.dao.spring.UserRepository;
import com.warehouse.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        UserAccount userAccount = userRepository.findByName(name);
        if(userAccount == null) {
            throw  new  UsernameNotFoundException("Not found");
        }
        String password = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(password, userAccount.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(userAccount, null ,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
