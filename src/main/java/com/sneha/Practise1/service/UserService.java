package com.sneha.Practise1.service;


import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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

    public User getUserById(Long id) {
        Optional<User> user_fetched = userRepository.findById(id);
        if(user_fetched.isPresent()) {
            return user_fetched.get();
        }
        else {
            return null;
        }
    }


    public String updateUserById(Long id, User user) {
        Optional<User> user_fetched =  userRepository.findById(id);
        if(user_fetched.isPresent()) {
            if (Objects.nonNull(user_fetched.get().getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
                user.setUsername(user.getUsername());
            }
            if (Objects.nonNull(user_fetched.get().getAge())) {
                user.setAge(user.getAge());
            }
            if (Objects.nonNull(user_fetched.get()) && !"".equalsIgnoreCase(user.getUsername())) {
                user.setUsername(user.getUsername());
            }
            userRepository.save(user);
            return "USER UPDATED BY ADMIN";
        }
        else {
            return "USER NOT FOUND";
        }
    }
}
