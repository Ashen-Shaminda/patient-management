package com.asd.patient_service.mappers;

import com.asd.patient_service.domain.dtos.PatientRequestDTO;
import com.asd.patient_service.domain.dtos.PatientResponseDTO;
import com.asd.patient_service.domain.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
   @Mapping(target = "id", source = "id")
   @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
   PatientResponseDTO toDTO(Patient patient);

   @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
//   @Mapping(target = "registeredDate", source = "registeredDate", dateFormat = "yyyy-MM-dd")
   Patient toEntity(PatientRequestDTO patientRequestDTO);
}
