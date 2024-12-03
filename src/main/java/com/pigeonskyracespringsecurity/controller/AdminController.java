package com.pigeonskyracespringsecurity.controller;

import com.pigeonskyracespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/secure")
    public String securedEndpoint() {
        return "You have access to this secured endpoint!";
    }

  //  private final UserService userService;

//    @PostMapping("/createUser")
//    public String createUser(@RequestParam String username, @RequestParam String password) {
//        try {
//            userService.createUser(username, password);
//            return "User created successfully!";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }



//    @PostMapping("/changeUserRole")
//    public String changeUserRole(@RequestParam String username, @RequestParam String newRole, @RequestParam boolean isAdmin) {
//        try {
//            userService.changeRole(username, newRole, isAdmin);
//            return "User role updated successfully!";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
}
