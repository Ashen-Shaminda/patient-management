package com.asd.patient_service.controllers;

import com.asd.patient_service.domain.dtos.PatientRequestDTO;
import com.asd.patient_service.domain.dtos.PatientResponseDTO;
import com.asd.patient_service.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
   final private PatientService patientService;

   public PatientController(PatientService patientService) {
      this.patientService = patientService;
   }

   @GetMapping
   public ResponseEntity<List<PatientResponseDTO>> getPatients() {
      List<PatientResponseDTO> patients = patientService.getPatients();

      return ResponseEntity.ok().body(patients);
   }

   @PostMapping
   public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
      PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

      return ResponseEntity.ok().body(patientResponseDTO);
   }
}


