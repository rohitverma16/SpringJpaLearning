package com.rohit.springjpademo.service;

import com.rohit.springjpademo.dto.ProfileRequestDto;
import com.rohit.springjpademo.entity.onetoone.Profile;
import com.rohit.springjpademo.entity.onetoone.User;
import com.rohit.springjpademo.mapper.ProfileMapper;
import com.rohit.springjpademo.repo.onetoone.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, UserService userService, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.userService = userService;
        this.profileMapper = profileMapper;
    }

    @Transactional
    public Profile createProfile(ProfileRequestDto profileRequestDto) {
        User user = userService.fetchUserById(profileRequestDto.userId()    );
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Profile profile = profileMapper.toEntity(profileRequestDto);
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    public List<Profile> fetchAll() {
        return profileRepository.findAll();
    }

    public Profile fetchProfileById(Long id) {
        return profileRepository.findById(id).orElseGet(()-> null);
    }
}
