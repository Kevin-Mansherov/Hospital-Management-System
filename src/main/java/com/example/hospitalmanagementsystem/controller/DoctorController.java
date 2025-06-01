package com.example.hospitalmanagementsystem.controller;


import com.example.hospitalmanagementsystem.Response;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public boolean checkDoctorData(Doctor doctor) {
        return doctor.getDoctorId() != null &&
                doctor.getFirstName() != null &&
                doctor.getLastName() != null &&
                doctor.getSpecialty() != null &&
                doctor.getContactNumber() != null;
    }

    //Get All Doctors
    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    //Get Doctor by ID
    @GetMapping("/getDoctorById")
    public ResponseEntity<Doctor> getDoctorById(@RequestParam String doctorId) {
        return doctorService.getDoctorById(doctorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    //Add Doctor
    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        if(!checkDoctorData(doctor)) {
            return ResponseEntity.badRequest().body("Invalid doctor data, try again.");
        }
        Response response = doctorService.addDoctor(doctor);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }

    //Update Doctor
    @PutMapping("/updateDoctor")
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor updatedDoctor) {
        if(!checkDoctorData(updatedDoctor)) {
            return ResponseEntity.badRequest().body("Invalid doctor data, try again.");
        }
        Response response = doctorService.updateDoctor(updatedDoctor);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }

    //Delete Doctor
    @DeleteMapping("/deleteDoctor")
    public ResponseEntity<String> deleteDoctor(@RequestParam String doctorId) {
        Response response = doctorService.deleteDoctor(doctorId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }
}
