package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.DTOs.PatientDto;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.response.StandardResponse;
import com.example.hospitalmanagementsystem.services.Patient.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //get all patients
    @GetMapping("allPatients")
    public ResponseEntity<StandardResponse> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients();
        StandardResponse response = new StandardResponse("success", patients, null);
        return ResponseEntity.ok(response);
    }

    //get patient by id
    @GetMapping("/getById/{patientId}")
    public ResponseEntity<StandardResponse> getPatientById(@PathVariable String patientId) {
        PatientDto patient = patientService.getPatientById(patientId);
        StandardResponse response = new StandardResponse("success", patient, null);
        return ResponseEntity.ok(response);
    }

    //add a new patient
    @PostMapping("addPatient")
    public ResponseEntity<StandardResponse> addPatient(@Valid @RequestBody PatientDto patientDto) {
        PatientDto addedPatient = patientService.addPatient(patientDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{patientId}")
                .buildAndExpand(addedPatient.getPatientId())
                .toUri();

        StandardResponse response = new StandardResponse("success", addedPatient, null);
        return ResponseEntity.created(location).body(response);
    }

    //update patient
    @PutMapping("/update/{patientId}")
    public ResponseEntity<StandardResponse> updatePatient(@PathVariable String patientId, @Valid @RequestBody PatientDto patientDto) {
        PatientDto updatedPatient = patientService.updatePatient(patientId, patientDto);
        StandardResponse response = new StandardResponse("success", updatedPatient, null);
        return ResponseEntity.ok(response);
    }

    //delete patient
    @DeleteMapping("/delete/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String patientId) {
        patientService.deletePatient(patientId);
    }
}
