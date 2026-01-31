package com.InsightLab.MedicalAppointmentBookingProject.repository;



import com.InsightLab.MedicalAppointmentBookingProject.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
