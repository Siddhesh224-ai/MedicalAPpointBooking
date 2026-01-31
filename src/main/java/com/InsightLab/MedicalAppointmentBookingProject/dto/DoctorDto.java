package com.InsightLab.MedicalAppointmentBookingProject.dto;

import lombok.Data;
import java.math.BigDecimal;

public class DoctorDto {

    @Data
    public static class DoctorRequest {
        private Long userId; // For creating a doctor profile linked to a user
        private int experienceYears;
        private BigDecimal consultationFee;
        private String about;
        private String licenseNumber;
    }

    @Data
    public static class DoctorResponse {
        private Long id;
        private UserDto.UserResponse user;
        private int experienceYears;
        private BigDecimal consultationFee;
        private String about;
        private String licenseNumber;
        private Double rating;
    }
}
