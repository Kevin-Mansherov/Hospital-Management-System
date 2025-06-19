package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.DTOs.AppointmentDto;
import com.example.hospitalmanagementsystem.response.StandardResponse;
import com.example.hospitalmanagementsystem.services.Appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get all appointments
    @GetMapping("/allAppointments")
    public ResponseEntity<StandardResponse> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        StandardResponse response = new StandardResponse("success", appointments, null);
        return ResponseEntity.ok(response);
    }

    // Get appointment by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<StandardResponse> getAppointmentById(@PathVariable int id) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        StandardResponse response = new StandardResponse("success", appointment, null);
        return ResponseEntity.ok(response);
    }

    // Add a new appointment
    @PostMapping("/addAppointment")
    public ResponseEntity<StandardResponse> addAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto addedAppointment = appointmentService.addAppointment(appointmentDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedAppointment.getId())
                .toUri();

        StandardResponse response = new StandardResponse("success", addedAppointment, null);
        return ResponseEntity.created(location).body(response);
    }

    // Update appointment
    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse> updateAppointment(@PathVariable int id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, appointmentDto);
        StandardResponse response = new StandardResponse("success", updatedAppointment, null);
        return ResponseEntity.ok(response);
    }

    // Delete appointment
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
    }
}
