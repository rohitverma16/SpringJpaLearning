package com.rohit.springjpademo.dto;

import com.rohit.springjpademo.entity.onetoone.User;

public record ProfileUserResponseDto(Long id, String name, String email, Long phone, UserDto user) {
}
