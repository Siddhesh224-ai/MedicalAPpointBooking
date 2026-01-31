package com.InsightLab.MedicalAppointmentBookingProject.repository;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}