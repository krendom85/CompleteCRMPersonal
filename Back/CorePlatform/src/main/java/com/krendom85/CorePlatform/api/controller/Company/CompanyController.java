package com.krendom85.CorePlatform.api.controller.Company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krendom85.CorePlatform.dto.ApiResponseDto;
import com.krendom85.CorePlatform.dto.Company.CompanyRequestDto;
import com.krendom85.CorePlatform.dto.Company.CompanyResponseDto;
import com.krendom85.CorePlatform.service.Company.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<CompanyResponseDto>> createCompany(@RequestBody CompanyRequestDto dto) {
        try {
            CompanyResponseDto created = companyService.create(dto);
            return ApiResponseDto.success(created, "Empresa creada correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return ApiResponseDto.failed(null, "Error al crear la empresa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CompanyResponseDto>> getCompany(@PathVariable Long id) {
        try {
            CompanyResponseDto company = companyService.getById(id);
            return ApiResponseDto.success(company, "Empresa encontrada", HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponseDto.failed(null, "Empresa no encontrada: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CompanyResponseDto>>> getAllCompanies() {
        try {
            List<CompanyResponseDto> companies = companyService.getAll();
            return ApiResponseDto.success(companies, "Listado de empresas", HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponseDto.failed(null, "Error al listar empresas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CompanyResponseDto>> updateCompany(@PathVariable Long id, @RequestBody CompanyRequestDto dto) {
        try {
            CompanyResponseDto updated = companyService.update(id, dto);
            return ApiResponseDto.success(updated, "Empresa actualizada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponseDto.failed(null, "Error al actualizar la empresa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteCompany(@PathVariable Long id) {
        try {
            companyService.delete(id);
            return ApiResponseDto.success(null, "Empresa eliminada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponseDto.failed(null, "Error al eliminar la empresa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

