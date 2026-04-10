package com.asd.patient_service.exception;

public class EmailAlreadyExistsException extends RuntimeException {
   public EmailAlreadyExistsException(String message) {
      super(message);
   }
}
