package com.krendom85.CorePlatform.api.controller.User;

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
import com.krendom85.CorePlatform.dto.User.UserRequestDto;
import com.krendom85.CorePlatform.dto.User.UserResponseDto;
import com.krendom85.CorePlatform.service.User.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(@RequestBody UserRequestDto dto) {
		try {
			UserResponseDto created = userService.create(dto);
			return ApiResponseDto.success(created, "Usuario creado correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al crear el usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto<UserResponseDto>> getUser(@PathVariable Long id) {
		try {
			UserResponseDto user = userService.getById(id);
			return ApiResponseDto.success(user, "Usuario encontrado", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Usuario no encontrado: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllUsers() {
		try {
			List<UserResponseDto> users = userService.getAll();
			return ApiResponseDto.success(users, "Listado de usuarios", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al listar usuarios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto) {
		try {
			UserResponseDto updated = userService.update(id, dto);
			return ApiResponseDto.success(updated, "Usuario actualizado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al actualizar el usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable Long id) {
		try {
			userService.delete(id);
			return ApiResponseDto.success(null, "Usuario eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return ApiResponseDto.failed(null, "Error al eliminar el usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
