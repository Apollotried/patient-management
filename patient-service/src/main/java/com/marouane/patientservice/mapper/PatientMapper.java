package com.marouane.patientservice.mapper;

import com.marouane.patientservice.dto.PatientRequestDto;
import com.marouane.patientservice.dto.PatientResponseDto;
import com.marouane.patientservice.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {

    public  PatientResponseDto toDto(Patient patient) {
        PatientResponseDto patientDto = new PatientResponseDto();
        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName());
        patientDto.setAddress(patient.getAddress());
        patientDto.setEmail(patient.getEmail());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDto;
    }

    public Patient toModel(PatientRequestDto patientDto) {
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setEmail(patientDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientDto.getRegisteredDate()));
        return patient;
    }
}
