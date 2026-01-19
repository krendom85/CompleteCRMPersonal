package com.krendom85.CorePlatform.service.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krendom85.CorePlatform.domain.Role.RoleEntity;
import com.krendom85.CorePlatform.domain.Permission.PermissionEntity;
import com.krendom85.CorePlatform.dto.Role.RoleRequestDto;
import com.krendom85.CorePlatform.dto.Role.RoleResponseDto;
import com.krendom85.CorePlatform.exception.ResourceNotFoundException;
import com.krendom85.CorePlatform.repository.Role.RoleRepository;
import com.krendom85.CorePlatform.repository.Permission.PermissionRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public RoleResponseDto create(RoleRequestDto dto) {
        RoleEntity entity = toEntity(dto);
        RoleEntity saved = roleRepository.save(entity);
        return toResponseDto(saved);
    }

    public RoleResponseDto update(Long id, RoleRequestDto dto) {
        RoleEntity entity = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        if (dto.getPermissionIds() != null) {
            List<PermissionEntity> permissions = permissionRepository.findAllById(dto.getPermissionIds());
            entity.setPermissions(new HashSet<>(permissions));
        }
        RoleEntity updated = roleRepository.save(entity);
        return toResponseDto(updated);
    }

    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rol no encontrado");
        }
        roleRepository.deleteById(id);
    }

    public RoleResponseDto getById(Long id) {
        RoleEntity entity = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        return toResponseDto(entity);
    }

    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private RoleEntity toEntity(RoleRequestDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        if (dto.getPermissionIds() != null) {
            List<PermissionEntity> permissions = permissionRepository.findAllById(dto.getPermissionIds());
            entity.setPermissions(new HashSet<>(permissions));
        }
        return entity;
    }

    private RoleResponseDto toResponseDto(RoleEntity entity) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        if (entity.getPermissions() != null) {
            dto.setPermissions(entity.getPermissions().stream()
                    .map(PermissionEntity::getName)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
