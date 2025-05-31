package com.example.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name= "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name= "doctor_id", nullable = false)
    private Doctor doctor;

    private Date appointmentDate;
    private Time appointmentTime;
    private String status; // e.g., "Scheduled", "Completed", "Cancelled"
}
