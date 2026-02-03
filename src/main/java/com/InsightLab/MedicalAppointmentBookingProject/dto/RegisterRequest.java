package com.InsightLab.MedicalAppointmentBookingProject.dto;

import com.InsightLab.MedicalAppointmentBookingProject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private String gender;
    private LocalDate dob;
}
