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
        // Check if the username already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists!");
        }

        // Fetch the Role entity based on the roleType from the DTO
        Role role = roleRepository.findByRoleType(userDTO.getRoleType())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role type!"));

        // Map the DTO to the User entity, including the Role assignment
        User user = userMapper.toUser(userDTO);
        user.setUsername(userDTO.getUsername());
        user.setRole(role);

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }





//    @Override
//    public User createUser(String username, String password){
//
//        if(userRepository.existsByUsername(username)){
//            throw new IllegalArgumentException("username already exists");
//        }
//
//        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER)
//                .orElseThrow(() -> new IllegalArgumentException("Default role not found"));
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setRole(role);
//
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User changeRole(String username, String newRole, boolean isAdmin){
//        if(!isAdmin){
//            throw new SecurityException("Only admins can change user roles");
//        }
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER)
//                .orElseThrow(() -> new IllegalArgumentException(" role not found"));
//
//        user.setRole(role);
//
//        return userRepository.save(user);
//    }


}
