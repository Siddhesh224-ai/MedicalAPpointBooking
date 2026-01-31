package com.InsightLab.MedicalAppointmentBookingProject.controller;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Appointment;
import com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot;
import com.InsightLab.MedicalAppointmentBookingProject.service.AppointmentService;
import com.InsightLab.MedicalAppointmentBookingProject.service.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @PostMapping("/book")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto.AppointmentResponse> bookAppointment(@RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto.AppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(request.getPatientId(), request.getSlotId());
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toAppointmentResponse(appointment));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto.AppointmentResponse>> getAppointmentsForPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(patientId);
        List<com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto.AppointmentResponse> responseDtos = appointments.stream()
                .map(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper::toAppointmentResponse)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto.AppointmentResponse>> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorId);
        List<com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto.AppointmentResponse> responseDtos = appointments.stream()
                .map(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper::toAppointmentResponse)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    // Helper endpoint to create slots for testing
    @PostMapping("/slots")
    public ResponseEntity<List<AppointmentSlot>> createSlots(
            @RequestParam Long doctorId,
            @RequestBody List<LocalDateTime> dateTimes) {
        List<AppointmentSlot> slots = doctorService.createAppointmentSlots(doctorId, dateTimes);
        return ResponseEntity.ok(slots);
    }
}
