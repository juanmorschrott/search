package com.avoris.infrastructure.exception;

import com.avoris.domain.exception.SearchNotFoundException;
import com.avoris.domain.exception.SearchServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String message = nonNull(ex.getDetailMessageArguments()) && Arrays.stream(ex.getDetailMessageArguments()).findFirst().isPresent()
                ? Arrays.stream(ex.getDetailMessageArguments()).findFirst().get().toString()
                : "Bad request";

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", 400);
        errorResponse.put("message", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleSearchNotFoundExceptions(SearchNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", 404);
        errorResponse.put("message", "Not Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SearchServiceException.class)
    public ResponseEntity<Map<String, Object>> handleSearchServiceExceptions(SearchServiceException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", 500);
        errorResponse.put("message", "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", 500);
        errorResponse.put("message", "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
