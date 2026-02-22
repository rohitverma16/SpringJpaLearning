package com.rohit.springjpademo.dto.profile;

import com.rohit.springjpademo.dto.user.UserDto;

public record ProfileUserResponseDto(Long id, String name, String email, Long phone, UserDto user) {
}
