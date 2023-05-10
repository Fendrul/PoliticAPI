package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.exceptions.FormFieldError;
import be.techni.PoliticAPI.exceptions.RessourceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RessourceNotFound.class)
    public ResponseEntity<?> handleRessourceNotFound(RessourceNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(FormFieldError.class)
    public ResponseEntity<?> handleFormFieldError(FormFieldError e) {
        return ResponseEntity.status(404).body(e.getResult().getAllErrors());
    }
}
