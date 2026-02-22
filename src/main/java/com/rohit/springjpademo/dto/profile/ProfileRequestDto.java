package com.rohit.springjpademo.dto.profile;

public record ProfileRequestDto(String name, String email, Long phone, Long userId) {
}
