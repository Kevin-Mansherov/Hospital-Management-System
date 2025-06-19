package com.example.hospitalmanagementsystem.services.Patient;

import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PatientDataInitializer implements CommandLineRunner {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientDataInitializer(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) {
        if (patientRepository.count() == 0) {
            // Example Patient objects
            patientRepository.save(new Patient("P001", "Alice", "Green", new Date(1994, 5, 12), "Female", "123 Main St", "555-1234"));
            patientRepository.save(new Patient("P002", "Bob", "White", new Date(1980, 8, 23), "Male", "456 Oak Ave", "555-5678"));
            patientRepository.save(new Patient("P003", "Charlie", "Black", new Date(1999, 1, 5), "Male", "789 Pine Rd", "555-9012"));
            patientRepository.save(new Patient("P004", "Diana", "Blue", new Date(1974, 11, 30), "Female", "321 Maple St", "555-3456"));
            patientRepository.save(new Patient("P005", "Eve", "Red", new Date(1989, 7, 17), "Female", "654 Cedar Ave", "555-7890"));
            System.out.println("Data initialization completed. Created 5 patient records.");
        } else {
            System.out.println("Database already contains records. Skipping initialization.");
        }
    }
}