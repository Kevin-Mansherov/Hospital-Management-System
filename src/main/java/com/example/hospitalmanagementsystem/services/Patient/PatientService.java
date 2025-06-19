package com.example.hospitalmanagementsystem.services.Patient;


import com.example.hospitalmanagementsystem.DTOs.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto addPatient(PatientDto patient);

    List<PatientDto> getAllPatients();

    PatientDto getPatientById(String patientId);

    PatientDto updatePatient(String patientId , PatientDto patient);

    void deletePatient(String patientId);
}
