package com.asd.patient_service.controllers;

import com.asd.patient_service.domain.dtos.PatientRequestDTO;
import com.asd.patient_service.domain.dtos.PatientResponseDTO;
import com.asd.patient_service.domain.dtos.validators.CreatePatientValidationGroup;
import com.asd.patient_service.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {
   final private PatientService patientService;

   public PatientController(PatientService patientService) {
      this.patientService = patientService;
   }

   @GetMapping
   @Operation(summary = "Get all patients", description = "Returns a list of all patients.")
   public ResponseEntity<List<PatientResponseDTO>> getPatients() {
      List<PatientResponseDTO> patients = patientService.getPatients();

      return ResponseEntity.ok().body(patients);
   }

   @PostMapping
   @Operation(summary = "Create a new patient", description = "Creates a new patient with the provided information.")
   public ResponseEntity<PatientResponseDTO> createPatient(
           @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
      PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

      return ResponseEntity.ok().body(patientResponseDTO);
   }

   @PutMapping(path = "/{id}")
   @Operation(summary = "Update an existing patient", description = "Updates the information of an existing patient identified by the provided ID.")
   public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                           @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
      PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);

      return ResponseEntity.ok().body(patientResponseDTO);
   }

   @DeleteMapping("/{id}")
   @Operation(summary = "Delete a patient", description = "Deletes the patient by the provided ID.")
   public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
      patientService.deletePatient(id);

      return ResponseEntity.noContent().build();
   }
}


