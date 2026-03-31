package com.asd.patient_service.domain.dtos;

import lombok.Data;

@Data
public class PatientResponseDTO {
   private String id;

   private String name;

   private String email;

   private String address;

   private String dateOfBirth;
}
