package com.rohit.springjpademo.dto.family;

import com.rohit.springjpademo.dto.user.UserDto;

public record FamilyResponseDto(String username, Long familyId, String familyName, String familyEmail) {
}
