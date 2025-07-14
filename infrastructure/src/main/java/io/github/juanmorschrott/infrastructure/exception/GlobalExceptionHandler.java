package io.github.juanmorschrott.infrastructure.exception;

import io.github.juanmorschrott.domain.exception.SearchNotFoundException;
import io.github.juanmorschrott.domain.exception.SearchServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, 400);

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Bad request");

        errorResponse.put(MESSAGE, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleSearchNotFoundExceptions(SearchNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, 404);
        errorResponse.put(MESSAGE, "Not Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SearchServiceException.class)
    public ResponseEntity<Map<String, Object>> handleSearchServiceExceptions(SearchServiceException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, 500);
        errorResponse.put(MESSAGE, "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(ERROR, 500);
        errorResponse.put(MESSAGE, "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
