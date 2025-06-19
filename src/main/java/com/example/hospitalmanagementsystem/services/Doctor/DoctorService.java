package com.example.hospitalmanagementsystem.services.Doctor;

import com.example.hospitalmanagementsystem.DTOs.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getAllDoctors();

    DoctorDto getDoctorById(String id);

    DoctorDto addDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(String id, DoctorDto doctorDto);

    void deleteDoctor(String id);
}
