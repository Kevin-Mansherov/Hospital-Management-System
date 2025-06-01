package com.example.hospitalmanagementsystem.service;


import com.example.hospitalmanagementsystem.Response;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.repository.AppointmentRepository;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    //Get All Appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get Appointment by ID
    public Optional<Appointment> getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    // Add Appointment
    public Response addAppointment(Appointment appointment) {
        boolean isValidDoctor = doctorRepository.existsById(appointment.getDoctor().getDoctorId());
        if(!isValidDoctor) {
            return new Response("Doctor not found with ID: " + appointment.getDoctor().getDoctorId(), false);
        }

        boolean isValidPatient = patientRepository.existsById(appointment.getPatient().getPatientId());
        if(!isValidPatient) {
            return new Response("Patient not found with ID: " + appointment.getPatient().getPatientId(), false);
        }

        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointment.getId());
        if (existingAppointment.isPresent()) {
            return new Response("Appointment already exists with ID: " + appointment.getId(), false);
        }
        appointmentRepository.save(appointment);
        return new Response("Appointment added successfully", true);
    }

    // Update Appointment
    public Response updateAppointment(Appointment updatedAppointment) {
        boolean isValidDoctor = doctorRepository.existsById(updatedAppointment.getDoctor().getDoctorId());
        if(!isValidDoctor) {
            return new Response("Doctor not found with ID: " + updatedAppointment.getDoctor().getDoctorId(), false);
        }

        boolean isValidPatient = patientRepository.existsById(updatedAppointment.getPatient().getPatientId());
        if(!isValidPatient) {
            return new Response("Patient not found with ID: " + updatedAppointment.getPatient().getPatientId(), false);
        }

        Optional<Appointment> existingAppointment = appointmentRepository.findById(updatedAppointment.getId());
        if (existingAppointment.isEmpty()) {
            return new Response("Appointment not found with ID: " + updatedAppointment.getId(), false);
        }
        appointmentRepository.save(updatedAppointment);
        return new Response("Appointment updated successfully", true);
    }

    // Delete Appointment
    public Response deleteAppointment(int appointmentId) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentId);
        if (existingAppointment.isEmpty()) {
            return new Response("Appointment not found with ID: " + appointmentId, false);
        }
        appointmentRepository.delete(existingAppointment.get());
        return new Response("Appointment deleted successfully", true);
    }
}
