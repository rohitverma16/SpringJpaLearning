package com.rohit.springjpademo.dto.family;

import com.rohit.springjpademo.dto.user.UserDto;

public record FamilyRequestDto(String familyName, String familyEmail, Long userId) {
}
