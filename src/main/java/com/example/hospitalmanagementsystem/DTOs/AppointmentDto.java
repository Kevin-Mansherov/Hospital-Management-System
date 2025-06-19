package com.example.hospitalmanagementsystem.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private int id;

    @NotNull(message = "Patient Data is required")
    private String patientId;

    @NotNull(message = "Doctor Data is required")
    private String doctorId;

    @NotNull(message="Appointment Date is required")
    private Date date;

    @NotNull(message = "Appointment Time is required")
    private Time time;

    @NotBlank(message = "Status is required")
    private String status; // e.g., "Scheduled", "Completed", "Cancelled"
}
