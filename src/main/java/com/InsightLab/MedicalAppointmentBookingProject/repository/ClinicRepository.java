package com.InsightLab.MedicalAppointmentBookingProject.repository;


import com.InsightLab.MedicalAppointmentBookingProject.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
