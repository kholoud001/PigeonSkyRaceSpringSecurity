package com.pigeonskyracespringsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!password.equals(userDetails.getPassword())) {
            throw new AuthenticationException("Invalid credentials") {};
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
