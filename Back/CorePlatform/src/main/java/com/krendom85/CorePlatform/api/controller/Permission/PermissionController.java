package com.krendom85.CorePlatform.api.controller.Permission;

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
import com.krendom85.CorePlatform.dto.Permission.PermissionRequestDto;
import com.krendom85.CorePlatform.dto.Permission.PermissionResponseDto;
import com.krendom85.CorePlatform.service.Permission.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<PermissionResponseDto>> createPermission(@RequestBody PermissionRequestDto dto) {
		try {
			PermissionResponseDto created = permissionService.create(dto);
			return ApiResponseDto.success(created, "Permiso creado correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al crear el permiso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<PermissionResponseDto>> getPermission(@PathVariable Long id) {
		try {
			PermissionResponseDto permission = permissionService.getById(id);
			return ApiResponseDto.success(permission, "Permiso encontrado", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Permiso no encontrado: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<PermissionResponseDto>>> getAllPermissions() {
		try {
			List<PermissionResponseDto> permissions = permissionService.getAll();
			return ApiResponseDto.success(permissions, "Listado de permisos", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al listar permisos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto<PermissionResponseDto>> updatePermission(@PathVariable Long id, @RequestBody PermissionRequestDto dto) {
		try {
			PermissionResponseDto updated = permissionService.update(id, dto);
			return ApiResponseDto.success(updated, "Permiso actualizado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al actualizar el permiso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto<Void>> deletePermission(@PathVariable Long id) {
		try {
			permissionService.delete(id);
			return ApiResponseDto.success(null, "Permiso eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al eliminar el permiso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
