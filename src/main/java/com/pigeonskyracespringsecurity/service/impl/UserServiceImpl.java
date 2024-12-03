package com.pigeonskyracespringsecurity.service.impl;

import com.pigeonskyracespringsecurity.DTO.UserDTO;
import com.pigeonskyracespringsecurity.mapper.UserMapper;
import com.pigeonskyracespringsecurity.model.entity.*;
import com.pigeonskyracespringsecurity.model.enums.RoleType;
import com.pigeonskyracespringsecurity.repository.RoleRepository;
import com.pigeonskyracespringsecurity.repository.UserRepository;
import com.pigeonskyracespringsecurity.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public User register(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists!");
        }

//        Role role = roleRepository.findByRoleType(userDTO.getRoleType())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid role type!"));
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER)
                .orElseThrow(() -> new IllegalArgumentException("Role not found!"));


        User user = userMapper.toUser(userDTO);
        user.setUsername(userDTO.getUsername());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }



    @Override
    public User changeRole(String username, String newRole) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser == null || !currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            throw new SecurityException("Only admins can change user roles");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Role role = roleRepository.findByRoleType(RoleType.valueOf(newRole))
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        user.setRole(role);
        return userRepository.save(user);
    }


}
