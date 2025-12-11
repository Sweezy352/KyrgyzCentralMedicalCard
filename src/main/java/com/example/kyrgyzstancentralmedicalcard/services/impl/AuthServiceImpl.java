package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.security.JwtCore;
import com.example.kyrgyzstancentralmedicalcard.security.UserDetailsImpl;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    public AuthServiceImpl(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, JwtCore jwtCore) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtCore = jwtCore;
    }

    @Override
    public String login(String inn, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        inn,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtCore.jwtGenerator((UserDetails) authentication.getPrincipal());
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByInn(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
