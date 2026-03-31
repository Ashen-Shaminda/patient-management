package com.asd.patient_service.services.impl;

import com.asd.patient_service.domain.dtos.PatientResponseDTO;
import com.asd.patient_service.domain.entities.Patient;
import com.asd.patient_service.mappers.PatientMapper;
import com.asd.patient_service.repositories.PatientRepository;
import com.asd.patient_service.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
   private final PatientRepository patientRepository;
   private final PatientMapper patientMapper;

   public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
      this.patientRepository = patientRepository;
      this.patientMapper = patientMapper;
   }

   public List<PatientResponseDTO> getPatients() {
      List<Patient> patients = patientRepository.findAll();

      return patients.stream().map(patientMapper::toDTO).toList();
   }
}
