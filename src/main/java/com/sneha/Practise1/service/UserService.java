package com.sneha.Practise1.service;


import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String deleteUserById(Long id) {
        userRepository.deleteById(id);    // USE findById(id)
        return "USER DELETED";
    }


}
