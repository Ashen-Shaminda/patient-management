package com.asd.patient_service.services;

import com.asd.patient_service.domain.dtos.PatientRequestDTO;
import com.asd.patient_service.domain.dtos.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
   List<PatientResponseDTO> getPatients();

   PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

   PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO);

   void deletePatient(UUID id);
}
