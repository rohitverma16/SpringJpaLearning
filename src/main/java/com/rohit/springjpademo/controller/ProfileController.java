package com.rohit.springjpademo.controller;

import com.rohit.springjpademo.dto.ProfileRequestDto;
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
    public ResponseEntity<List<Profile>> fetchAllProfiles() {
        List<Profile> profiles = profileService.fetchAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> fetchProfile(@PathVariable Long id) {
        Profile profile=profileService.fetchProfileById(id);
        if(profile==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }
}
