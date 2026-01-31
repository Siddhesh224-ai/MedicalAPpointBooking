package com.InsightLab.MedicalAppointmentBookingProject.controller;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Clinic;
import com.InsightLab.MedicalAppointmentBookingProject.service.ClinicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicResponse> createClinic(@RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicRequest clinicRequest) {
        Clinic clinic = com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toClinicEntity(clinicRequest);
        Clinic createdClinic = clinicService.createClinic(clinic);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toClinicResponse(createdClinic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicResponse> getClinicById(@PathVariable Long id) {
        Clinic clinic = clinicService.getClinicById(id);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toClinicResponse(clinic));
    }

    @GetMapping
    public ResponseEntity<List<com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicResponse>> getAllClinics() {
        List<Clinic> clinics = clinicService.getAllClinics();
        List<com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicResponse> responseDtos = clinics.stream()
                .map(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper::toClinicResponse)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicResponse> updateClinic(@PathVariable Long id, @RequestBody com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto.ClinicRequest clinicDetails) {
        // Ideally mapper or service should handle update mapping
        Clinic tempClinic = com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toClinicEntity(clinicDetails);
        Clinic updatedClinic = clinicService.updateClinic(id, tempClinic);
        return ResponseEntity.ok(com.InsightLab.MedicalAppointmentBookingProject.mapper.DtoMapper.toClinicResponse(updatedClinic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
