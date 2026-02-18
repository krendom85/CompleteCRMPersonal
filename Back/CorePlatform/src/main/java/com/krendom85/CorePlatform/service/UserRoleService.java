package com.krendom85.CorePlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krendom85.CorePlatform.model.UserRole;
import com.krendom85.CorePlatform.model.UserRoleId;
import com.krendom85.CorePlatform.repository.UserRoleRepository;

@Service
public class UserRoleService {
     private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    public Optional<UserRole> findById(UserRoleId id) {
        return userRoleRepository.findById(id);
    }

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public void deleteById(UserRoleId id) {
        userRoleRepository.deleteById(id);
    }
}
