package com.InsightLab.MedicalAppointmentBookingProject.controller;

import com.InsightLab.MedicalAppointmentBookingProject.entity.User;
import com.InsightLab.MedicalAppointmentBookingProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserResponse> createUser(@RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserRequest userRequest) {
        User user = com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toUserEntity(userRequest);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toUserResponse(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toUserResponse(user));
    }

    @GetMapping
    public ResponseEntity<List<com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserResponse> responseDtos = users.stream()
                .map(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper::toUserResponse)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserResponse> updateUser(@PathVariable Long id, @RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto.UserRequest userDetails) {
        // Ideally we should have an update method in Mapper or Service that takes DTO
        // For now, mapping request to a temporary user object to pass to service
        User tempUser = com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toUserEntity(userDetails);
        User updatedUser = userService.updateUser(id, tempUser);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toUserResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
