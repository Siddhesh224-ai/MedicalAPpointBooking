package com.InsightLab.MedicalAppointmentBookingProject.service;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Doctor;
import com.InsightLab.MedicalAppointmentBookingProject.entity.User;
import com.InsightLab.MedicalAppointmentBookingProject.repository.DoctorRepository;
import com.InsightLab.MedicalAppointmentBookingProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final com.InsightLab.MedicalAppointmentBookingProject.repository.AppointmentSlotRepository appointmentSlotRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         UserRepository userRepository,
                         com.InsightLab.MedicalAppointmentBookingProject.repository.AppointmentSlotRepository appointmentSlotRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.appointmentSlotRepository = appointmentSlotRepository;
    }

    public List<com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot> createAppointmentSlots(Long doctorId, List<java.time.LocalDateTime> dateTimes) {
        Doctor doctor = getDoctorById(doctorId);
        List<com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot> slots = new java.util.ArrayList<>();

        for (java.time.LocalDateTime time : dateTimes) {
            com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot slot = new com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot();
            slot.setDoctor(doctor);
            slot.setSlotTime(time);
            slot.setBooked(false);
            slots.add(slot);
        }
        return appointmentSlotRepository.saveAll(slots);
    }

    public Doctor createDoctor(Long userId, Doctor doctor) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new com.InsightLab.MedicalAppointmentBookingProject.exception.ResourceNotFoundException("User not found with id: " + userId));

        doctor.setUser(user);
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new com.InsightLab.MedicalAppointmentBookingProject.exception.ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);

        doctor.setExperienceYears(doctorDetails.getExperienceYears());
        doctor.setConsultationFee(doctorDetails.getConsultationFee());
        doctor.setAbout(doctorDetails.getAbout());
        doctor.setLicenseNumber(doctorDetails.getLicenseNumber());
        // Specializations update logic can be complex, for now simple set
        if (doctorDetails.getSpecializations() != null) {
            doctor.setSpecializations(doctorDetails.getSpecializations());
        }

        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}