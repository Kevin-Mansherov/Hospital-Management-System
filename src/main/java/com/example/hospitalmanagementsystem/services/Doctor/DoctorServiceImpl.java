package com.example.hospitalmanagementsystem.services.Doctor;

import com.example.hospitalmanagementsystem.DTOs.DoctorDto;
import com.example.hospitalmanagementsystem.exceptions.AlreadyExists;
import com.example.hospitalmanagementsystem.exceptions.EntityAndIdMisMatch;
import com.example.hospitalmanagementsystem.exceptions.NotExists;
import com.example.hospitalmanagementsystem.mappers.DoctorMapper;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorDto getDoctorById(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotExists("Doctor with ID " + id + " does not exist"));
        return doctorMapper.toDto(doctor);
    }

    @Override
    @Transactional
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        if (doctorRepository.existsById(doctorDto.getDoctorId())) {
            throw new AlreadyExists("Doctor with ID " + doctorDto.getDoctorId() + " already exists");
        }

        Doctor doctor = doctorMapper.toEntity(doctorDto);
        Doctor added = doctorRepository.save(doctor);
        return doctorMapper.toDto(added);
    }

    @Override
    @Transactional
    public DoctorDto updateDoctor(String id, DoctorDto doctorDto) {
        if (doctorDto.getDoctorId() != null && !doctorDto.getDoctorId().equals(id)) {
            throw new EntityAndIdMisMatch("Doctor ID mismatch: provided ID " + doctorDto.getDoctorId() + " does not match path ID " + id);
        }

        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotExists("Doctor with ID " + id + " does not exist"));

        doctorMapper.updateEntityFromDto(doctorDto, existingDoctor);
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return doctorMapper.toDto(updatedDoctor);
    }

    @Override
    @Transactional
    public void deleteDoctor(String id) {
        if (!doctorRepository.existsById(id)) {
            throw new NotExists("Doctor with ID " + id + " does not exist");
        }
        doctorRepository.deleteById(id);
    }
}
