package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.Response;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    //Get All Doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    //Get Doctor by ID
    public Optional<Doctor> getDoctorById(String doctorId) {
        return doctorRepository.findById(doctorId);
    }

    //Add Doctor
    public Response addDoctor(Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctor.getDoctorId());
        if (existingDoctor.isPresent()) {
            return new Response("Doctor already exists with ID: " + doctor.getDoctorId(), false);
        }
        doctorRepository.save(doctor);
        return new Response("Doctor added successfully", true);
    }

    //Update Doctor
    public Response updateDoctor(Doctor updatedDoctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(updatedDoctor.getDoctorId());
        if (existingDoctor.isEmpty()) {
            return new Response("Doctor not found with ID: " + updatedDoctor.getDoctorId(), false);
        }
        doctorRepository.save(updatedDoctor);
        return new Response("Doctor updated successfully", true);
    }

    //Delete Doctor
    public Response deleteDoctor(String doctorId) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctorId);
        if (existingDoctor.isEmpty()) {
            return new Response("Doctor not found with ID: " + doctorId, false);
        }
        doctorRepository.delete(existingDoctor.get());
        return new Response("Doctor deleted successfully", true);
    }
}
