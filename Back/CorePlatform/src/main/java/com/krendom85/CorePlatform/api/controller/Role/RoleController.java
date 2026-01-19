package com.krendom85.CorePlatform.api.controller.Role;

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
import com.krendom85.CorePlatform.dto.Role.RoleRequestDto;
import com.krendom85.CorePlatform.dto.Role.RoleResponseDto;
import com.krendom85.CorePlatform.service.Role.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<RoleResponseDto>> createRole(@RequestBody RoleRequestDto dto) {
		try {
			RoleResponseDto created = roleService.create(dto);
			return ApiResponseDto.success(created, "Rol creado correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al crear el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<RoleResponseDto>> getRole(@PathVariable Long id) {
		try {
			RoleResponseDto role = roleService.getById(id);
			return ApiResponseDto.success(role, "Rol encontrado", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Rol no encontrado: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<RoleResponseDto>>> getAllRoles() {
		try {
			List<RoleResponseDto> roles = roleService.getAll();
			return ApiResponseDto.success(roles, "Listado de roles", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al listar roles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto<RoleResponseDto>> updateRole(@PathVariable Long id, @RequestBody RoleRequestDto dto) {
		try {
			RoleResponseDto updated = roleService.update(id, dto);
			return ApiResponseDto.success(updated, "Rol actualizado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al actualizar el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto<Void>> deleteRole(@PathVariable Long id) {
		try {
			roleService.delete(id);
			return ApiResponseDto.success(null, "Rol eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al eliminar el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
