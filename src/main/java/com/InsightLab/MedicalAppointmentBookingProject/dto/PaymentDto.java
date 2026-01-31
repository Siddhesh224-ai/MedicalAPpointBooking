package com.InsightLab.MedicalAppointmentBookingProject.dto;

import lombok.Data;

public class PaymentDto {

    @Data
    public static class OrderRequest {
        private Double amount;
    }

    @Data
    public static class OrderResponse {
        private String orderId;
        private String currency;
        private Integer amount;
        private String keyId; // Added for frontend access
        private String status;
    }

    @Data
    public static class PaymentVerificationRequest {
        private String razorpayOrderId;
        private String razorpayPaymentId;
        private String razorpaySignature;
    }
}
