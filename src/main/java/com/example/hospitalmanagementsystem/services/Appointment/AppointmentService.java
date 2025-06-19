package com.example.hospitalmanagementsystem.services.Appointment;



import com.example.hospitalmanagementsystem.DTOs.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> getAllAppointments();

    AppointmentDto getAppointmentById(int id);

    AppointmentDto addAppointment(AppointmentDto appointmentDto);

    AppointmentDto updateAppointment(int id, AppointmentDto appointmentDto);

    void deleteAppointment(int id);
}
