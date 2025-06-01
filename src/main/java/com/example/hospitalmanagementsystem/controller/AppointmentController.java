package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.Response;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public boolean checkAppointmentData(Appointment appointment) {
        return appointment.getPatient() != null &&
                appointment.getDoctor() != null &&
                appointment.getAppointmentDate() != null &&
                appointment.getStatus() != null;
    }

    // Get All Appointments
    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get Appointment by ID
    @GetMapping("/getAppointmentById")
    public ResponseEntity<Appointment> getAppointmentById(int appointmentId) {
        return appointmentService.getAppointmentById(appointmentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    // Add Appointment
    @PostMapping("/addAppointment")
    public ResponseEntity<String> addAppointment(Appointment appointment) {
        if (!checkAppointmentData(appointment)) {
            return ResponseEntity.badRequest().body("Invalid appointment data, try again.");
        }
        Response response = appointmentService.addAppointment(appointment);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }

    // Update Appointment
    @PostMapping("/updateAppointment")
    public ResponseEntity<String> updateAppointment(Appointment updatedAppointment) {
        if (!checkAppointmentData(updatedAppointment)) {
            return ResponseEntity.badRequest().body("Invalid appointment data, try again.");
        }
        Response response = appointmentService.updateAppointment(updatedAppointment);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }

    // Delete Appointment
    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<String> deleteAppointment(int appointmentId) {
        Response response = appointmentService.deleteAppointment(appointmentId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }
}
