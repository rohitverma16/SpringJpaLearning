package com.rohit.springjpademo.mapper;

import com.rohit.springjpademo.dto.ProfileRequestDto;
import com.rohit.springjpademo.entity.onetoone.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    public Profile toEntity(ProfileRequestDto profileRequestDto) {
          Profile profile=new Profile();
          profile.setName(profileRequestDto.name());
          profile.setEmail(profileRequestDto.email());
          profile.setPhone(profileRequestDto.phone());
          return profile;
    }
}
