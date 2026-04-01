package com.asd.patient_service.services;

import com.asd.patient_service.domain.dtos.PatientRequestDTO;
import com.asd.patient_service.domain.dtos.PatientResponseDTO;

import java.util.List;

public interface PatientService {
   List<PatientResponseDTO> getPatients();

   PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
}
