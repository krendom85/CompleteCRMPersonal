package com.krendom85.CorePlatform.service.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krendom85.CorePlatform.domain.Permission.PermissionEntity;
import com.krendom85.CorePlatform.dto.Permission.PermissionRequestDto;
import com.krendom85.CorePlatform.dto.Permission.PermissionResponseDto;
import com.krendom85.CorePlatform.exception.ResourceNotFoundException;
import com.krendom85.CorePlatform.repository.Permission.PermissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionResponseDto create(PermissionRequestDto dto) {
        PermissionEntity entity = toEntity(dto);
        PermissionEntity saved = permissionRepository.save(entity);
        return toResponseDto(saved);
    }

    public PermissionResponseDto update(Long id, PermissionRequestDto dto) {
        PermissionEntity entity = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        PermissionEntity updated = permissionRepository.save(entity);
        return toResponseDto(updated);
    }

    public void delete(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Permiso no encontrado");
        }
        permissionRepository.deleteById(id);
    }

    public PermissionResponseDto getById(Long id) {
        PermissionEntity entity = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado"));
        return toResponseDto(entity);
    }

    public List<PermissionResponseDto> getAll() {
        return permissionRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private PermissionEntity toEntity(PermissionRequestDto dto) {
        PermissionEntity entity = new PermissionEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    private PermissionResponseDto toResponseDto(PermissionEntity entity) {
        PermissionResponseDto dto = new PermissionResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
