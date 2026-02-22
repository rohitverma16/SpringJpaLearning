package com.rohit.springjpademo.mapper;

import com.rohit.springjpademo.dto.family.FamilyRequestDto;
import com.rohit.springjpademo.dto.family.FamilyResponseDto;
import com.rohit.springjpademo.dto.profile.ProfileRequestDto;
import com.rohit.springjpademo.dto.profile.ProfileUserResponseDto;
import com.rohit.springjpademo.dto.user.UserDto;
import com.rohit.springjpademo.dto.user.UserprofileRequestDto;
import com.rohit.springjpademo.entity.onetomany.Family;
import com.rohit.springjpademo.entity.onetoone.Profile;
import com.rohit.springjpademo.entity.onetoone.User;
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

    public Family toEntity(FamilyRequestDto familyRequestDto) {
        Family family=new Family();
        family.setFamilyEmail(familyRequestDto.familyEmail());
        family.setFamilyName(familyRequestDto.familyName());
        return family;
    }

    public User toEntity(UserprofileRequestDto userprofileRequestDto) {
        User user=new User();
        user.setUsername(userprofileRequestDto.username());
        user.setStatus(userprofileRequestDto.status());

        // Create Profile entity
        Profile profile=new Profile();
        profile.setUser(userprofileRequestDto.profile().getUser());
        profile.setName(userprofileRequestDto.profile().getName());
        profile.setEmail(userprofileRequestDto.profile().getEmail());
        profile.setPhone(userprofileRequestDto.profile().getPhone());

        // Link both sides
        user.setProfile(profile);
        profile.setUser(user); // important for bi-directional mapping
        return user;
    }

    public ProfileUserResponseDto toDto(Profile profile) {
        if(profile==null) return null;
        return new ProfileUserResponseDto(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getPhone(),
                toDto(profile.getUser())
        );
    }

    public UserDto toDto(User user) {
        if(user==null) return null;
        return new UserDto(user.getId(), user.getUsername(), user.getStatus());
    }

    public FamilyResponseDto toDto(Family family) {
        if(family==null) return null;
        return new FamilyResponseDto(
                family.getUser().getUsername(),
                family.getFamilyId(),
                family.getFamilyName(),
                family.getFamilyEmail()
        );
    }
}
