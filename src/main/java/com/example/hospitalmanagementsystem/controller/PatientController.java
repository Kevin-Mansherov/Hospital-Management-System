package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.Response;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //check patient data
    public boolean checkPatientData(Patient patient){
        return patient.getPatientId() != null && patient.getFirstName() != null && patient.getLastName() != null &&
                patient.getDateOfBirth() != null && patient.getGender() != null && patient.getAddress() != null &&
                patient.getContactNumber() != null; // Invalid data
    }

    //Add Patient
    @PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        System.out.println("\n[INFO] - Create user attempt with patient: " + patient.toString() + "\n");
        if(!checkPatientData(patient)){
            return ResponseEntity.badRequest().body("Invalid patient data, try again.");
        }
        Response response = patientService.addPatient(patient);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }
    //Get All Patients
    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    //Get Patient by ID
    @GetMapping("/getPatientById")
    public ResponseEntity<Patient> getPatientById(@RequestParam String patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);

        if(patient.isPresent()){
            return ResponseEntity.ok(patient.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    //Update Patient
    @PutMapping("/updatePatient")
    public ResponseEntity<String> updatePatient(@RequestBody Patient updatedPatient) {
        System.out.println("\n[INFO] - Update patient attempt with patient: " + updatedPatient.toString() + "\n");
        if(!checkPatientData(updatedPatient)){
            return ResponseEntity.badRequest().body("Invalid patient data, try again.");
        }
        Response response = patientService.updatePatient(updatedPatient);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(400).body(response.getMessage());
        }
    }

    //Delete Patient
    @DeleteMapping("/deletePatient")
    public ResponseEntity<String> deletePatient(@RequestParam String patientId) {
        System.out.println("\n[INFO] - Delete patient attempt with ID: " + patientId + "\n");
        if (patientId == null || patientId.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid patient ID, try again.");
        }
        Response response = patientService.deletePatient(patientId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }
}
