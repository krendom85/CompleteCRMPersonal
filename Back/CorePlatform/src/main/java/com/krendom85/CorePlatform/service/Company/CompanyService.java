package com.krendom85.CorePlatform.service.Company;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krendom85.CorePlatform.domain.Company.CompanyEntity;
import com.krendom85.CorePlatform.domain.enums.StatesEnum;
import com.krendom85.CorePlatform.dto.Company.CompanyRequestDto;
import com.krendom85.CorePlatform.dto.Company.CompanyResponseDto;
import com.krendom85.CorePlatform.exception.ResourceNotFoundException;
import com.krendom85.CorePlatform.repository.Company.CompanyRepository;

@Service
public class CompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyResponseDto create (CompanyRequestDto dto){
        CompanyEntity entity = new CompanyEntity();
        entity.setName(dto.getName());
        entity.setRuc(dto.getRuc());
        entity.setAddress(dto.getAddress());
        entity.setActive(StatesEnum.ACTIVE);
        CompanyEntity saved = companyRepository.save(entity);
        return toResponseDto(saved);
    }

    public CompanyResponseDto update (Long id, CompanyRequestDto dto){
        CompanyEntity entity = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compania no creada"));
        entity.setName(dto.getName());
        entity.setRuc(dto.getRuc());
        entity.setAddress(dto.getAddress());
        CompanyEntity updated = companyRepository.save(entity);
        return toResponseDto(updated);
    }

    public void delete(Long id){
        if(!companyRepository.existsById(id))
        {
            throw new ResourceNotFoundException("Compania no creada");
        }
        companyRepository.deleteById(id);
    }

    public CompanyResponseDto getById(Long id) {
        CompanyEntity entity = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return toResponseDto(entity);
    }

    public List<CompanyResponseDto> getAll() {
        return companyRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private CompanyResponseDto toResponseDto(CompanyEntity entity) {
        CompanyResponseDto dto = new CompanyResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRuc(entity.getRuc());
        dto.setAddress(entity.getAddress());
        dto.setActive(entity.getActive().name());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
    
}
