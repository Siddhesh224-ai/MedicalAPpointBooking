package com.InsightLab.MedicalAppointmentBookingProject.repository;
import com.InsightLab.MedicalAppointmentBookingProject.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository
        extends JpaRepository<Prescription, Long> {
}