package com.guuh.scheduler_bff.infrastructure.handler;

import com.guuh.scheduler_bff.infrastructure.exceptions.IntegrationException;
import com.guuh.scheduler_bff.infrastructure.exceptions.ConflictException;
import com.guuh.scheduler_bff.infrastructure.exceptions.ResourceNotFoundException;
import com.guuh.scheduler_bff.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<RestErrorMessage> ConflictHandler(ConflictException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<RestErrorMessage> ResourceNotFoundHandler(ResourceNotFoundException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<RestErrorMessage> UnauthorizesHandler(UnauthorizedException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(IntegrationException.class)
    private ResponseEntity<RestErrorMessage> IntegrationHandler(IntegrationException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_GATEWAY, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(threatResponse);
    }



}

