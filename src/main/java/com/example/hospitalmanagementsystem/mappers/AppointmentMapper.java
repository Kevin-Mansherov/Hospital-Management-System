package com.example.hospitalmanagementsystem.mappers;

import com.example.hospitalmanagementsystem.DTOs.AppointmentDto;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    //Transfer To Entity
    public Appointment toEntity(AppointmentDto appointmentDto,Patient patient, Doctor doctor) {
        if (appointmentDto == null) return null;

        Appointment entity = new Appointment();
        entity.setId(appointmentDto.getId());
        entity.setPatient(patient);
        entity.setDoctor(doctor);
        entity.setAppointmentDate(appointmentDto.getDate());
        entity.setAppointmentTime(appointmentDto.getTime());
        entity.setStatus(appointmentDto.getStatus());

        return entity;
    }
    //Transfer To DTO
    public AppointmentDto toDto(Appointment appointment) {
        if (appointment == null) return null;

        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setPatientId(appointment.getPatient() != null ? appointment.getPatient().getPatientId() : null);
        dto.setDoctorId(appointment.getDoctor() != null ? appointment.getDoctor().getDoctorId() : null);
        dto.setDate(appointment.getAppointmentDate());
        dto.setTime(appointment.getAppointmentTime());
        dto.setStatus(appointment.getStatus());

        return dto;
    }

    //Update Entity from DTO
    public void updateEntityFromDto(AppointmentDto appointmentDto, Appointment appointment, Patient patient, Doctor doctor) {
        if (appointmentDto == null || appointment == null) {
            return;
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDto.getDate());
        appointment.setAppointmentTime(appointmentDto.getTime());
        appointment.setStatus(appointmentDto.getStatus());
    }
}
