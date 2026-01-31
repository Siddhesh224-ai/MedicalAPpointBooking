package com.InsightLab.MedicalAppointmentBookingProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "prescriptions")
@Getter @Setter
public class Prescription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Appointment appointment;

    @Column(length = 2000)
    private String diagnosis;

    @Column(length = 2000)
    private String advice;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Medicine> medicines;
}