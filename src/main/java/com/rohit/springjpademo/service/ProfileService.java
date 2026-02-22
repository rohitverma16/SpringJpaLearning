package com.rohit.springjpademo.service;

import com.rohit.springjpademo.dto.profile.ProfileRequestDto;
import com.rohit.springjpademo.dto.profile.ProfileUserResponseDto;
import com.rohit.springjpademo.entity.onetoone.Profile;
import com.rohit.springjpademo.entity.onetoone.User;
import com.rohit.springjpademo.mapper.ProfileMapper;
import com.rohit.springjpademo.repo.ProfileRepository;
import com.rohit.springjpademo.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.profileMapper = profileMapper;
    }

    @Transactional
    public Profile createProfile(ProfileRequestDto profileRequestDto) {
        User user = userRepository.findById(profileRequestDto.userId()).orElseThrow(()->new RuntimeException("User not found"));
        Profile profile = profileMapper.toEntity(profileRequestDto);
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    public List<ProfileUserResponseDto> fetchAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(profileMapper::toDto).toList();
    }

    public ProfileUserResponseDto fetchProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElseGet(() -> null);
        return profileMapper.toDto(profile);
    }
}
