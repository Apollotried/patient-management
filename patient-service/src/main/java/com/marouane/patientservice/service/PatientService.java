package com.marouane.patientservice.service;

import com.marouane.patientservice.dto.PatientRequestDto;
import com.marouane.patientservice.dto.PatientResponseDto;
import com.marouane.patientservice.exception.EmailAlreadyExistsException;
import com.marouane.patientservice.exception.PatientNotFoundException;
import com.marouane.patientservice.grpc.BillingServiceGrpcClient;
import com.marouane.patientservice.kafka.KafkaProducer;
import com.marouane.patientservice.mapper.PatientMapper;
import com.marouane.patientservice.model.Patient;
import com.marouane.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, BillingServiceGrpcClient billingServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponseDto> getPatients(){
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.getEmail());
        }
        Patient newPatient = patientRepository.save(patientMapper.toModel(patientRequestDto));

        billingServiceGrpcClient.createBillingAccount(
                newPatient.getId().toString(),
                newPatient.getName(),
                newPatient.getEmail()
        );

        kafkaProducer.sendEvent(newPatient);

        return patientMapper.toDto(newPatient);
    }

    public PatientResponseDto updatePatient(
            UUID id, PatientRequestDto patientRequestDto){

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)){
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.getEmail());
        }

        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        return patientMapper.toDto(patientRepository.save(patient));
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }


}
