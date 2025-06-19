package com.example.hospitalmanagementsystem.mappers;


import com.example.hospitalmanagementsystem.DTOs.PatientDto;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.stereotype.Component;


@Component
public class PatientMapper {

    //Transfer To Entity
    public Patient toEntity(PatientDto patientDto){
        if(patientDto == null) return null;

        Patient entity = new Patient();
        entity.setPatientId(patientDto.getPatientId());
        entity.setFirstName(patientDto.getFirstName());
        entity.setLastName(patientDto.getLastName());
        entity.setDateOfBirth(patientDto.getDateOfBirth());
        entity.setGender(patientDto.getGender());
        entity.setAddress(patientDto.getAddress());
        entity.setContactNumber(patientDto.getContactNumber());

        return entity;
    }
    //Transfer To DTO
    public PatientDto toDto(Patient patient){
        if(patient == null) return null;

        PatientDto dto = new PatientDto();
        dto.setPatientId(patient.getPatientId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender());
        dto.setAddress(patient.getAddress());
        dto.setContactNumber(patient.getContactNumber());
        return dto;
    }
    //Update Entity from DTO
    public void updateEntityFromDto(PatientDto patientDto, Patient patient){
        if (patientDto == null || patient == null) {
            return;
        }

        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setGender(patientDto.getGender());
        patient.setAddress(patientDto.getAddress());
        patient.setContactNumber(patientDto.getContactNumber());
    }
}
