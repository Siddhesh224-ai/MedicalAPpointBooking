package com.InsightLab.MedicalAppointmentBookingProject.repository;

import com.InsightLab.MedicalAppointmentBookingProject.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAvailabilityRepository
        extends JpaRepository<DoctorAvailability, Long> {
}
