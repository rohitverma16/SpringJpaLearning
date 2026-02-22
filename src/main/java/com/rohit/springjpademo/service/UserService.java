package com.rohit.springjpademo.service;

import com.rohit.springjpademo.dto.user.UserprofileRequestDto;
import com.rohit.springjpademo.entity.onetoone.User;
import com.rohit.springjpademo.mapper.ProfileMapper;
import com.rohit.springjpademo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileMapper profileMapper;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    public User fetchUserById(Long id) {
        return userRepository.findById(id).orElseGet(()->null);
    }

    public User createUser(UserprofileRequestDto userprofileRequestDto) {
        User user = profileMapper.toEntity(userprofileRequestDto);
        return userRepository.save(user);
    }
}
