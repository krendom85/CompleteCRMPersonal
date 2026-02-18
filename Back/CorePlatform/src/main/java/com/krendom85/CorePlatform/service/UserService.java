package com.krendom85.CorePlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krendom85.CorePlatform.model.User;
import com.krendom85.CorePlatform.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
