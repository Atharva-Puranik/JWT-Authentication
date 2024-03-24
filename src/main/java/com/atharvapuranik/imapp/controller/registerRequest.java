package com.atharvapuranik.imapp.controller;


import com.atharvapuranik.imapp.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class registerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
