package com.InsightLab.MedicalAppointmentBookingProject.dto;

import com.InsightLab.MedicalAppointmentBookingProject.enums.AppointmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

public class AppointmentDto {

    @Data
    public static class AppointmentRequest {
        private Long patientId;
        private Long slotId;
    }

    @Data
    public static class AppointmentResponse {
        private Long id;
        private Long patientId;
        private String patientName;
        private Long doctorId;
        private String doctorName;
        private Long clinicId;
        private String clinicName;
        private LocalDateTime appointmentTime;
        private AppointmentStatus status;
    }
}
