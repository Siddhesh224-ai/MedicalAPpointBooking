package com.InsightLab.MedicalAppointmentBookingProject.repository;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
}