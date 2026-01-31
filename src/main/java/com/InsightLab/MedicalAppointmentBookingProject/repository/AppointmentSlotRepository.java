package com.InsightLab.MedicalAppointmentBookingProject.repository;


import com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSlotRepository
        extends JpaRepository<AppointmentSlot, Long> {
}