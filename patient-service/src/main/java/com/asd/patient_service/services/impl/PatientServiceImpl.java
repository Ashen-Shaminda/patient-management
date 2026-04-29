package com.asd.patient_service.services.impl;

import com.asd.patient_service.domain.dtos.PatientRequestDTO;
import com.asd.patient_service.domain.dtos.PatientResponseDTO;
import com.asd.patient_service.domain.entities.Patient;
import com.asd.patient_service.exception.EmailAlreadyExistsException;
import com.asd.patient_service.exception.PatientNotFoundException;
import com.asd.patient_service.grpc.BillingServiceGrpcClient;
import com.asd.patient_service.mappers.PatientMapper;
import com.asd.patient_service.repositories.PatientRepository;
import com.asd.patient_service.services.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
   private final PatientRepository patientRepository;
   private final PatientMapper patientMapper;
   private final BillingServiceGrpcClient billingServiceGrpcClient;

   public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, BillingServiceGrpcClient billingServiceGrpcClient) {
      this.patientRepository = patientRepository;
      this.patientMapper = patientMapper;
      this.billingServiceGrpcClient = billingServiceGrpcClient;
   }

   public List<PatientResponseDTO> getPatients() {
      List<Patient> patients = patientRepository.findAll();

      return patients.stream().map(patientMapper::toDTO).toList();
   }

   @Override
   public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
      if (patientRepository.existsByEmail(patientRequestDTO.getEmail()))
         throw new EmailAlreadyExistsException("Email already exists");

      Patient newPatient = patientRepository.save(patientMapper.toEntity(patientRequestDTO));

      billingServiceGrpcClient.createBillingAccount(
              newPatient.getId().toString(),
              newPatient.getName(),
              newPatient.getEmail()
      );

      return patientMapper.toDTO(newPatient);
   }

   @Override
   public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
      Patient patient = patientRepository.findById(id).orElseThrow(() ->
              new PatientNotFoundException("Patient not found with ID: " + id));

      if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id))
         throw new EmailAlreadyExistsException("A patient with this email " + patientRequestDTO.getEmail() + " already exists");

      patient.setName(patientRequestDTO.getName());
      patient.setAddress(patientRequestDTO.getAddress());
      patient.setEmail(patientRequestDTO.getEmail());
      patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

      Patient updatedPatient = patientRepository.save(patient);

      return patientMapper.toDTO(updatedPatient);
   }

   @Override
   public void deletePatient(UUID id) {
      patientRepository.deleteById(id);
   }
}