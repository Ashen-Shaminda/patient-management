package com.asd.patient_service.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PatientResponseDTO {
   private String id;

   private String name;

   private String email;

   private String address;

   private String dateOfBirth;
}
