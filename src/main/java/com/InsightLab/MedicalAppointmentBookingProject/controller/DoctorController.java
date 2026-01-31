package com.InsightLab.MedicalAppointmentBookingProject.controller;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Doctor;
import com.InsightLab.MedicalAppointmentBookingProject.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorResponse> createDoctor(@RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorRequest doctorRequest) {
        // Here we need userId from DTO
        // Ideally validation that userId exists happens in Service
        Doctor doctor = com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toDoctorEntity(doctorRequest);
        Doctor createdDoctor = doctorService.createDoctor(doctorRequest.getUserId(), doctor);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toDoctorResponse(createdDoctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorResponse> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toDoctorResponse(doctor));
    }

    @GetMapping
    public ResponseEntity<List<com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorResponse>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorResponse> responseDtos = doctors.stream()
                .map(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper::toDoctorResponse)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorResponse> updateDoctor(@PathVariable Long id, @RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto.DoctorRequest doctorDetails) {
        Doctor tempDoctor = com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toDoctorEntity(doctorDetails);
        Doctor updatedDoctor = doctorService.updateDoctor(id, tempDoctor);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toDoctorResponse(updatedDoctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
