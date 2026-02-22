package com.rohit.springjpademo.controller;

import com.rohit.springjpademo.dto.profile.ProfileRequestDto;
import com.rohit.springjpademo.dto.profile.ProfileUserResponseDto;
import com.rohit.springjpademo.entity.onetoone.Profile;
import com.rohit.springjpademo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileRequestDto profileRequestDto) {
        Profile profileSaved=profileService.createProfile(profileRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileSaved);
    }

    @GetMapping
    public ResponseEntity<List<ProfileUserResponseDto>> fetchAllProfiles() {
        List<ProfileUserResponseDto> profileUserResponseDto = profileService.fetchAll();
        return ResponseEntity.ok(profileUserResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileUserResponseDto> fetchProfile(@PathVariable Long id) {
        ProfileUserResponseDto profileUserResponseDto=profileService.fetchProfileById(id);
        if(profileUserResponseDto==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileUserResponseDto);
    }
}
