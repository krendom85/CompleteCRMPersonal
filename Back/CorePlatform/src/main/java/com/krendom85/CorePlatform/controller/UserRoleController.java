package com.krendom85.CorePlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krendom85.CorePlatform.model.UserRole;
import com.krendom85.CorePlatform.model.UserRoleId;
import com.krendom85.CorePlatform.service.UserRoleService;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
      private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public List<UserRole> getAll() {
        return userRoleService.findAll();
    }

    @GetMapping("/{userId}/{roleId}")
    public Optional<UserRole> getById(@PathVariable Long userId, @PathVariable Long roleId) {
        return userRoleService.findById(new UserRoleId(userId, roleId));
    }

    @PostMapping
    public UserRole create(@RequestBody UserRole userRole) {
        return userRoleService.save(userRole);
    }

    @DeleteMapping("/{userId}/{roleId}")
    public void delete(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.deleteById(new UserRoleId(userId, roleId));
    }
}
