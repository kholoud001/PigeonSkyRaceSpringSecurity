package com.pigeonskyracespringsecurity.service;

import com.pigeonskyracespringsecurity.DTO.UserDTO;
import com.pigeonskyracespringsecurity.exception.UsernameAlreadyExistsException;
import com.pigeonskyracespringsecurity.model.entity.User;

public interface UserService {

    User register(UserDTO userDTO) throws UsernameAlreadyExistsException;


    User changeRole(String username, String newRole);
}
