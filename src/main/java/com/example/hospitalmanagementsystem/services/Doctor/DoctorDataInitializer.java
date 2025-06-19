package com.example.hospitalmanagementsystem.services.Doctor;

import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DoctorDataInitializer implements CommandLineRunner {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorDataInitializer(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) {
        if (doctorRepository.count() == 0) {
            // Example Doctor objects
            doctorRepository.save(new Doctor("D001", "John", "Smith", "Cardiology", "555-1111", "john.smith@hospital.com"));
            doctorRepository.save(new Doctor("D002", "Emily", "Johnson", "Neurology", "555-2222", "emily.johnson@hospital.com"));
            doctorRepository.save(new Doctor("D003", "Michael", "Williams", "Pediatrics", "555-3333", "michael.williams@hospital.com"));
            doctorRepository.save(new Doctor("D004", "Sarah", "Brown", "Orthopedics", "555-4444", "sarah.brown@hospital.com"));
            doctorRepository.save(new Doctor("D005", "David", "Jones", "Dermatology", "555-5555", "david.jones@hospital.com"));
            System.out.println("Data initialization completed. Created 5 doctor records.");
        } else {
            System.out.println("Database already contains records. Skipping initialization.");
        }
    }
}
