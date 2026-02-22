package com.rohit.springjpademo.controller;

import com.rohit.springjpademo.dto.family.FamilyRequestDto;
import com.rohit.springjpademo.dto.family.FamilyResponseDto;
import com.rohit.springjpademo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDto> createFamily(@RequestBody FamilyRequestDto familyRequestDto) {
        FamilyResponseDto family = familyService.createFamily(familyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(family);
    }

    @GetMapping
    public ResponseEntity<List<FamilyResponseDto>> getAllFamilies() {
        List<FamilyResponseDto> allFamily = familyService.fetchAllFamily();
        return ResponseEntity.status(HttpStatus.OK).body(allFamily);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyResponseDto> getFamilyById(@PathVariable Long id) {
        FamilyResponseDto familyResponseDto = familyService.fetchFamilyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(familyResponseDto);
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<List<FamilyResponseDto>> getFamilyByUserId(@PathVariable Long id) {
        List<FamilyResponseDto> familyResponseDto = familyService.fetchFamilyByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(familyResponseDto);
    }
}
