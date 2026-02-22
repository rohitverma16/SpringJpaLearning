package com.rohit.springjpademo.service;

import com.rohit.springjpademo.dto.family.FamilyRequestDto;
import com.rohit.springjpademo.dto.family.FamilyResponseDto;
import com.rohit.springjpademo.entity.onetomany.Family;
import com.rohit.springjpademo.entity.onetoone.User;
import com.rohit.springjpademo.mapper.ProfileMapper;
import com.rohit.springjpademo.repo.FamilyRepository;
import com.rohit.springjpademo.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    public FamilyService(FamilyRepository familyRepository,UserRepository userRepository, ProfileMapper profileMapper) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.profileMapper = profileMapper;
    }

    @Transactional
    public FamilyResponseDto createFamily(FamilyRequestDto familyRequestDto) {
        User user = userRepository.findById(familyRequestDto.userId()).orElseThrow(()->new RuntimeException("User Not Found"));
        Family family = profileMapper.toEntity(familyRequestDto);
        family.setUser(user);
        Family familyCreated = familyRepository.save(family);
        return profileMapper.toDto(familyCreated);
    }

    public List<FamilyResponseDto> fetchAllFamily(){
        List<Family> families = familyRepository.findAll();
        return families.stream().map(profileMapper::toDto).toList();
    }

    public FamilyResponseDto fetchFamilyById(Long id){
        Family family = familyRepository.findById(id).orElseGet(() -> null);
        return profileMapper.toDto(family);
    }

    public List<FamilyResponseDto> fetchFamilyByUserId(Long id){
        List<Family> families = familyRepository.findByUserId(id);
        return families.stream().map(profileMapper::toDto).toList();
    }

}
