package com.InsightLab.MedicalAppointmentBookingProject.dto;

import com.InsightLab.MedicalAppointmentBookingProject.enums.Role;
import lombok.Data;

import java.time.LocalDate;

public class UserDto {

    @Data
    public static class UserRequest {
        private String name;
        private String email;
        private String phone;
        private String passwordHash;
        private Role role;
        private String gender;
        private LocalDate dob;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private Role role;
        private String gender;
        private LocalDate dob;
    }
}
