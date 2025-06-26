package com.example.HabitaCode.domain.entity.dto;

public class LoginDTO {
    public record LoginRequestDTO(String username, String password) {}
    public record LoginResponseDTO(String userId, String token) {}
}
