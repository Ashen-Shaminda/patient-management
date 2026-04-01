package com.asd.patient_service.controllers;

import com.asd.patient_service.domain.dtos.PatientResponseDTO;
import com.asd.patient_service.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
   private PatientService patientService;

   public PatientController(PatientService patientService) {
      this.patientService = patientService;
   }

   @GetMapping
   public ResponseEntity<List<PatientResponseDTO>> getPatients() {
      List<PatientResponseDTO> patients = patientService.getPatients();

      return ResponseEntity.ok().body(patients);
   }
}

