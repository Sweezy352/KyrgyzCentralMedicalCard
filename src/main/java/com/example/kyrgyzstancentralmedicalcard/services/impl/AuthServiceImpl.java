package com.example.kyrgyzstancentralmedicalcard.services.impl;

import com.example.kyrgyzstancentralmedicalcard.entity.Role;
import com.example.kyrgyzstancentralmedicalcard.entity.User;
import com.example.kyrgyzstancentralmedicalcard.repository.RoleRepository;
import com.example.kyrgyzstancentralmedicalcard.repository.UserRepository;
import com.example.kyrgyzstancentralmedicalcard.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByInn(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User login(String inn, String password) {
        User user = userRepository.findByInn(inn)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Wrong password");
        }
        return user;
    }

    @Override
    public void create(User user) {
        Role role = roleRepository.findByRoleName("USER").orElseThrow(() -> new RuntimeException("Роль не найдена"));
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
