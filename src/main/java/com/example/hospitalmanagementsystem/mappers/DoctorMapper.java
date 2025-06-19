package com.example.hospitalmanagementsystem.mappers;


import com.example.hospitalmanagementsystem.DTOs.DoctorDto;
import com.example.hospitalmanagementsystem.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    //Transfer To Entity
    public Doctor toEntity(DoctorDto doctorDto) {
        if (doctorDto == null) return null;

        Doctor entity = new Doctor();
        entity.setDoctorId(doctorDto.getDoctorId());
        entity.setFirstName(doctorDto.getFirstName());
        entity.setLastName(doctorDto.getLastName());
        entity.setSpecialty(doctorDto.getSpecialty());
        entity.setContactNumber(doctorDto.getContactNumber());

        return entity;
    }
    //Transfer To DTO
    public DoctorDto toDto(Doctor entity) {
        if (entity == null) return null;

        DoctorDto dto = new DoctorDto();
        dto.setDoctorId(entity.getDoctorId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setSpecialty(entity.getSpecialty());
        dto.setContactNumber(entity.getContactNumber());

        return dto;
    }
    //Update Entity from DTO
    public void updateEntityFromDto(DoctorDto doctorDto, Doctor entity) {
        if (doctorDto == null || entity == null) {
            return;
        }

        entity.setFirstName(doctorDto.getFirstName());
        entity.setLastName(doctorDto.getLastName());
        entity.setSpecialty(doctorDto.getSpecialty());
        entity.setContactNumber(doctorDto.getContactNumber());
    }
}
