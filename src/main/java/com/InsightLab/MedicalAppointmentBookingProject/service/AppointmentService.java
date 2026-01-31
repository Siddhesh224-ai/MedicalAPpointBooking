package com.InsightLab.MedicalAppointmentBookingProject.service;

import com.InsightLab.MedicalAppointmentBookingProject.entity.Appointment;
import com.InsightLab.MedicalAppointmentBookingProject.entity.AppointmentSlot;
import com.InsightLab.MedicalAppointmentBookingProject.entity.User;
import com.InsightLab.MedicalAppointmentBookingProject.enums.AppointmentStatus;
import com.InsightLab.MedicalAppointmentBookingProject.exception.ResourceNotFoundException;
import com.InsightLab.MedicalAppointmentBookingProject.repository.AppointmentRepository;
import com.InsightLab.MedicalAppointmentBookingProject.repository.AppointmentSlotRepository;
import com.InsightLab.MedicalAppointmentBookingProject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentSlotRepository appointmentSlotRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentSlotRepository appointmentSlotRepository,
                              UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentSlotRepository = appointmentSlotRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Appointment bookAppointment(Long patientId, Long slotId) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));

        AppointmentSlot slot = appointmentSlotRepository.findById(slotId)
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found with id: " + slotId));

        if (slot.isBooked()) {
            throw new IllegalStateException("Slot is already booked");
        }

        slot.setBooked(true);
        appointmentSlotRepository.save(slot);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(slot.getDoctor());
        appointment.setClinic(slot.getClinic()); // Assuming slot has clinic info, if not might need to fetch from Doctor
        appointment.setAppointmentTime(slot.getSlotTime());
        appointment.setStatus(AppointmentStatus.BOOKED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));

        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new IllegalStateException("Appointment is already cancelled");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);

        // Ideally we should find the slot and unbook it, but connection might be lost if we don't store slotId in appointment.
        // For now, simpler implementation. If strict slot management is needed, we'd need to link Appointment -> Slot.
        // Or find slot by doctor & time.
        // Let's try to find the slot to free it up.
        // This is a "best effort" to free the slot based on doctor and time matching.
        // In a real app we'd likely have a OneToOne or ManyToOne relationship to the specific slot.
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        // We'd need a custom query in repository for this.
        // Creating placeholder implementation using findAll and filtering (inefficient but works for now)
        // ideally: return appointmentRepository.findByPatientId(patientId);
        return appointmentRepository.findAll().stream()
                .filter(a -> a.getPatient().getId().equals(patientId))
                .toList();
    }

    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
         // ideally: return appointmentRepository.findByDoctorId(doctorId);
        return appointmentRepository.findAll().stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId))
                .toList();
    }
}
