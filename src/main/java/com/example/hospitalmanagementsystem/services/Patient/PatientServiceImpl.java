package com.example.hospitalmanagementsystem.services.Patient;

import com.example.hospitalmanagementsystem.DTOs.PatientDto;
import com.example.hospitalmanagementsystem.exceptions.AlreadyExists;
import com.example.hospitalmanagementsystem.exceptions.EntityAndIdMisMatch;
import com.example.hospitalmanagementsystem.exceptions.NotExists;
import com.example.hospitalmanagementsystem.mappers.PatientMapper;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDto> getAllPatients(){
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDto getPatientById(String patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotExists("Patient with ID: " + patientId + " does not exist."));
        return patientMapper.toDto(patient);
    }

    @Override
    @Transactional
    public PatientDto addPatient(PatientDto patientDto) {
        if (patientRepository.existsById(patientDto.getPatientId())) {
            throw new AlreadyExists("Patient with ID: " + patientDto.getPatientId() + " already exists.");
        }
        Patient patient = patientMapper.toEntity(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDto(savedPatient);
    }

    @Override
    @Transactional
    public PatientDto updatePatient(String patientId, PatientDto patientDto) {
        if(patientDto.getPatientId() != null && !patientDto.getPatientId().equals(patientId)){
            throw new EntityAndIdMisMatch("Student ID mismatch: provided ID " + patientDto.getPatientId() + " does not match path ID " + patientId);
        }

        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotExists("Patient with ID: " + patientId + " does not exist."));

        patientMapper.updateEntityFromDto(patientDto, existingPatient);
        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDto(updatedPatient);
    }

    @Override
    @Transactional
    public void deletePatient(String patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new NotExists("Patient with ID: " + patientId + " does not exist.");
        }
        patientRepository.deleteById(patientId);
    }
}
