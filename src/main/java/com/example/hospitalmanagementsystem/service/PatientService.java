package com.example.hospitalmanagementsystem.service;


import com.example.hospitalmanagementsystem.Response;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //Add Patient
    public Response addPatient(Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findById(patient.getPatientId());
        if (existingPatient.isPresent()) {
            return new Response("Patient already exists with ID: " + patient.getPatientId(), false);
        }
        patientRepository.save(patient);
        return new Response("Patient added successfully", true);
    }

    //Get All Patients
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    //Get Patient by ID
    public Optional<Patient> getPatientById(String patientId) {
        return patientRepository.findById(patientId);
    }

    //Update Patient
    public Response updatePatient(Patient updatedPatient) {
        Optional<Patient> existingPatient = patientRepository.findById(updatedPatient.getPatientId());
        if (existingPatient.isEmpty()) {
            return new Response("Patient not found with ID: " + updatedPatient.getPatientId(), false);
        }
        patientRepository.save(updatedPatient);
        return new Response("Patient updated successfully", true);
    }

    //Delete Patient
    public Response deletePatient(String patientId) {
        Optional<Patient> existingPatient = patientRepository.findById(patientId);
        if (existingPatient.isEmpty()) {
            return new Response("Patient not found with ID: " + patientId, false);
        }
        patientRepository.delete(existingPatient.get());
        return new Response("Patient deleted successfully", true);
    }
}
