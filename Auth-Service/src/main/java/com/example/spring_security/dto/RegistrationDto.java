package com.example.spring_security.dto;

import com.example.spring_security.enums.TypeUser;

public record RegistrationDto(
        String email, String password, TypeUser user
        ) {
}
