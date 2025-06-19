package com.example.hospitalmanagementsystem.services.Appointment;

import com.example.hospitalmanagementsystem.DTOs.AppointmentDto;
import com.example.hospitalmanagementsystem.exceptions.AlreadyExists;
import com.example.hospitalmanagementsystem.exceptions.EntityAndIdMisMatch;
import com.example.hospitalmanagementsystem.exceptions.NotExists;
import com.example.hospitalmanagementsystem.mappers.AppointmentMapper;
import com.example.hospitalmanagementsystem.model.Appointment;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.AppointmentRepository;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import com.example.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper,
                                  PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDto getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotExists("Appointment with ID " + id + " does not exist"));
        return appointmentMapper.toDto(appointment);
    }

    @Override
    @Transactional
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        if (appointmentRepository.existsById(appointmentDto.getId())) {
            throw new AlreadyExists("Appointment with ID " + appointmentDto.getId() + " already exists");
        }
        Patient patient = patientRepository.getById(appointmentDto.getPatientId());
        if (patient == null) {
            throw new NotExists("Patient with ID " + appointmentDto.getPatientId() + " does not exist");
        }
        Doctor doctor = doctorRepository.getById(appointmentDto.getDoctorId());
        if (doctor == null) {
            throw new NotExists("Doctor with ID " + appointmentDto.getDoctorId() + " does not exist");
        }
        Appointment appointment = appointmentMapper.toEntity(appointmentDto, patient, doctor);
        Appointment added = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(added);
    }

    @Override
    @Transactional
    public AppointmentDto updateAppointment(int id, AppointmentDto appointmentDto) {
        if (appointmentDto.getId() != id) {
            throw new EntityAndIdMisMatch("Appointment ID mismatch: provided ID " + appointmentDto.getId() + " does not match path ID " + id);
        }

        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotExists("Appointment with ID " + id + " does not exist"));

        Patient patient = patientRepository.getById(appointmentDto.getPatientId());
        if (patient == null) {
            throw new NotExists("Patient with ID " + appointmentDto.getPatientId() + " does not exist");
        }
        Doctor doctor = doctorRepository.getById(appointmentDto.getDoctorId());
        if (doctor == null) {
            throw new NotExists("Doctor with ID " + appointmentDto.getDoctorId() + " does not exist");
        }

        appointmentMapper.updateEntityFromDto(appointmentDto, existingAppointment, patient, doctor);
        Appointment updated = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteAppointment(int id) {
        if (!appointmentRepository.existsById(id)) {
            throw new NotExists("Appointment with ID " + id + " does not exist");
        }
        appointmentRepository.deleteById(id);
    }

}