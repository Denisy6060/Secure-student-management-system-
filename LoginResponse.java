package com.example.studentmanagement.auth;

import com.example.studentmanagement.user.Role;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        String username,
        Role role) {
}
