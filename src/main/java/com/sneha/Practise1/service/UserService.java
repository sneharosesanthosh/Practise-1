package com.sneha.Practise1.service;

import com.sneha.Practise1.entity.Admin;
import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveuser(User user) {
        return userRepository.save(user);
    }

}
