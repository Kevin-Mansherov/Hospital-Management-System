package com.example.hospitalmanagementsystem.controller;


import com.example.hospitalmanagementsystem.DTOs.DoctorDto;
import com.example.hospitalmanagementsystem.response.StandardResponse;
import com.example.hospitalmanagementsystem.services.Doctor.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Get all doctors
    @GetMapping("/allDoctors")
    public ResponseEntity<StandardResponse> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        StandardResponse response = new StandardResponse("success", doctors, null);
        return ResponseEntity.ok(response);
    }

    // Get doctor by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<StandardResponse> getDoctorById(@PathVariable String id) {
        DoctorDto doctor = doctorService.getDoctorById(id);
        StandardResponse response = new StandardResponse("success", doctor, null);
        return ResponseEntity.ok(response);
    }

    // Add a new doctor
    @PostMapping("/addDoctor")
    public ResponseEntity<StandardResponse> addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto addedDoctor = doctorService.addDoctor(doctorDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedDoctor.getDoctorId())
                .toUri();
        StandardResponse response = new StandardResponse("success", addedDoctor, null);
        return ResponseEntity.created(location).body(response);
    }

    // Update doctor
    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse> updateDoctor(@PathVariable String id, @Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto updatedDoctor = doctorService.updateDoctor(id, doctorDto);
        StandardResponse response = new StandardResponse("success", updatedDoctor, null);
        return ResponseEntity.ok(response);
    }

    // Delete doctor
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
    }
}
