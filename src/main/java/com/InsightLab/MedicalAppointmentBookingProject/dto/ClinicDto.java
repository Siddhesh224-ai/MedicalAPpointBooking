package com.InsightLab.MedicalAppointmentBookingProject.dto;

import lombok.Data;

public class ClinicDto {

    @Data
    public static class ClinicRequest {
        private String name;
        private String address;
        private String city;
        private Double latitude;
        private Double longitude;
    }

    @Data
    public static class ClinicResponse {
        private Long id;
        private String name;
        private String address;
        private String city;
        private Double latitude;
        private Double longitude;
    }
}
