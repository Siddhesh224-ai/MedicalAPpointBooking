package com.InsightLab.MedicalAppointmentBookingProject.mapper;

import com.InsightLab.MedicalAppointmentBookingProject.dto.AppointmentDto;
import com.InsightLab.MedicalAppointmentBookingProject.dto.ClinicDto;
import com.InsightLab.MedicalAppointmentBookingProject.dto.DoctorDto;
import com.InsightLab.MedicalAppointmentBookingProject.dto.UserDto;
import com.InsightLab.MedicalAppointmentBookingProject.entity.Appointment;
import com.InsightLab.MedicalAppointmentBookingProject.entity.Clinic;
import com.InsightLab.MedicalAppointmentBookingProject.entity.Doctor;
import com.InsightLab.MedicalAppointmentBookingProject.entity.User;

public class DtoMapper {

    // User Mappers
    public static User toUserEntity(UserDto.UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPasswordHash(request.getPasswordHash());
        user.setRole(request.getRole());
        user.setGender(request.getGender());
        user.setDob(request.getDob());
        return user;
    }

    public static UserDto.UserResponse toUserResponse(User user) {
        UserDto.UserResponse response = new UserDto.UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setGender(user.getGender());
        response.setDob(user.getDob());
        return response;
    }

    // Clinic Mappers
    public static Clinic toClinicEntity(ClinicDto.ClinicRequest request) {
        Clinic clinic = new Clinic();
        clinic.setName(request.getName());
        clinic.setAddress(request.getAddress());
        clinic.setCity(request.getCity());
        clinic.setLatitude(request.getLatitude());
        clinic.setLongitude(request.getLongitude());
        return clinic;
    }

    public static ClinicDto.ClinicResponse toClinicResponse(Clinic clinic) {
        ClinicDto.ClinicResponse response = new ClinicDto.ClinicResponse();
        response.setId(clinic.getId());
        response.setName(clinic.getName());
        response.setAddress(clinic.getAddress());
        response.setCity(clinic.getCity());
        response.setLatitude(clinic.getLatitude());
        response.setLongitude(clinic.getLongitude());
        return response;
    }

    // Doctor Mappers
    public static Doctor toDoctorEntity(DoctorDto.DoctorRequest request) {
        Doctor doctor = new Doctor();
        doctor.setExperienceYears(request.getExperienceYears());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setAbout(request.getAbout());
        doctor.setLicenseNumber(request.getLicenseNumber());
        return doctor;
    }

    public static DoctorDto.DoctorResponse toDoctorResponse(Doctor doctor) {
        DoctorDto.DoctorResponse response = new DoctorDto.DoctorResponse();
        response.setId(doctor.getId());
        response.setUser(toUserResponse(doctor.getUser()));
        response.setExperienceYears(doctor.getExperienceYears());
        response.setConsultationFee(doctor.getConsultationFee());
        response.setAbout(doctor.getAbout());
        response.setLicenseNumber(doctor.getLicenseNumber());
        response.setRating(doctor.getRating());
        return response;
    }

    // Appointment Mappers
    public static AppointmentDto.AppointmentResponse toAppointmentResponse(Appointment appointment) {
        AppointmentDto.AppointmentResponse response = new AppointmentDto.AppointmentResponse();
        response.setId(appointment.getId());
        response.setPatientId(appointment.getPatient().getId());
        response.setPatientName(appointment.getPatient().getName());
        response.setDoctorId(appointment.getDoctor().getId());
        // Assume Doctor has a 'User' linked, get name from there if needed, 
        // or if Doctor entity has name field (it doesn't, it has User).
        // Let's rely on Doctor's User's name.
        if (appointment.getDoctor().getUser() != null) {
            response.setDoctorName(appointment.getDoctor().getUser().getName());
        }
        
        if (appointment.getClinic() != null) {
            response.setClinicId(appointment.getClinic().getId());
            response.setClinicName(appointment.getClinic().getName());
        }
        
        response.setAppointmentTime(appointment.getAppointmentTime());
        response.setStatus(appointment.getStatus());
        return response;
    }
}
