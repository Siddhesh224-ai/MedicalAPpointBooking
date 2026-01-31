package com.InsightLab.MedicalAppointmentBookingProject.service;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Clinic;
import com.InsightLab.MedicalAppointmentBookingProject.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public Clinic getClinicById(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new com.InsightLab.MedicalAppointmentBookingProject.exception.ResourceNotFoundException("Clinic not found with id: " + id));
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Clinic updateClinic(Long id, Clinic clinicDetails) {
        Clinic clinic = getClinicById(id);

        clinic.setName(clinicDetails.getName());
        clinic.setAddress(clinicDetails.getAddress());
        clinic.setCity(clinicDetails.getCity());
        clinic.setLatitude(clinicDetails.getLatitude());
        clinic.setLongitude(clinicDetails.getLongitude());

        return clinicRepository.save(clinic);
    }

    public void deleteClinic(Long id) {
        Clinic clinic = getClinicById(id);
        clinicRepository.delete(clinic);
    }
}