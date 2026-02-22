package com.rohit.springjpademo.service;

import com.rohit.springjpademo.entity.onetoone.User;
import com.rohit.springjpademo.repo.onetoone.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    public User fetchUserById(Long id) {
        return userRepository.findById(id).orElseGet(()->null);
    }
}
