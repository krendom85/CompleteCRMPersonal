package com.krendom85.CorePlatform.service.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krendom85.CorePlatform.domain.User.UserEntity;
import com.krendom85.CorePlatform.domain.Company.CompanyEntity;
import com.krendom85.CorePlatform.domain.Role.RoleEntity;
import com.krendom85.CorePlatform.dto.User.UserRequestDto;
import com.krendom85.CorePlatform.dto.User.UserResponseDto;
import com.krendom85.CorePlatform.exception.ResourceNotFoundException;
import com.krendom85.CorePlatform.repository.User.UserRepository;
import com.krendom85.CorePlatform.repository.Company.CompanyRepository;
import com.krendom85.CorePlatform.repository.Role.RoleRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserResponseDto create(UserRequestDto dto) {
        UserEntity entity = toEntity(dto);
        UserEntity saved = userRepository.save(entity);
        return toResponseDto(saved);
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : entity.getStatus());
        if (dto.getCompanyId() != null) {
            CompanyEntity company = companyRepository.findById(dto.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));
            entity.setCompany(company);
        }
        if (dto.getRoleIds() != null) {
            List<RoleEntity> roles = roleRepository.findAllById(dto.getRoleIds());
            entity.setRoles(new java.util.HashSet<>(roles));
        }
        UserEntity updated = userRepository.save(entity);
        return toResponseDto(updated);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto getById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return toResponseDto(entity);
    }

    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private UserEntity toEntity(UserRequestDto dto) {
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        if (dto.getCompanyId() != null) {
            CompanyEntity company = companyRepository.findById(dto.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));
            entity.setCompany(company);
        }
        if (dto.getRoleIds() != null) {
            List<RoleEntity> roles = roleRepository.findAllById(dto.getRoleIds());
            entity.setRoles(new java.util.HashSet<>(roles));
        }
        return entity;
    }

    private UserResponseDto toResponseDto(UserEntity entity) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setStatus(entity.getStatus() != null ? entity.getStatus().toString() : null);
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
            dto.setCompanyName(entity.getCompany().getName());
        }
        if (entity.getRoles() != null) {
            dto.setRoles(entity.getRoles().stream()
                    .map(RoleEntity::getName)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
