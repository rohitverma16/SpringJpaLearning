package com.rohit.springjpademo.dto.user;

import com.rohit.springjpademo.entity.onetoone.Profile;

public record UserprofileRequestDto(String username, Boolean status, Profile profile) {
}
