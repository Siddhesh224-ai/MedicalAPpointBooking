package com.InsightLab.MedicalAppointmentBookingProject.repository;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository
        extends JpaRepository<Medicine, Long> {
}
