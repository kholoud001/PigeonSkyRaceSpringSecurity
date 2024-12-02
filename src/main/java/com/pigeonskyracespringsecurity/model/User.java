package com.pigeonskyracespringsecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique=true)
    @NotBlank(message = "Username must be entered")
    private String username;
    @NotBlank(message = "please provide a password")
    private String password;
}
