package org.example.checklistapp.config;

import jakarta.servlet.http.HttpServletRequest;
import org.example.checklistapp.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Missing or malformed JSON body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMissingBody(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                400,
                "Bad Request",
                "Request body is missing or malformed",
                request.getRequestURI()
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    // When @Valid checks incoming request and validation fails, will throw MethodArgumentNotValidException
    // If we do not include this handleValidationErrors, spring will just return a generic response code back
    // We need to handle the exception here and return a suitable response
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(error.getField(), error.getDefaultMessage())
                );

        ApiError error = new ApiError(
                400,
                "Bad Request",
                "Validation failed",
                request.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();

//        ex.getBindingResult()
//                .getFieldErrors()
//                .forEach(error ->
//                        fieldErrors.put(error.getField(), error.getDefaultMessage())
//                );

        ApiError error = new ApiError(
                400,
                "Bad Request",
                "Validation failed",
                request.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleItemNotFound(
            ItemNotFoundException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ItemForAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleItemForAccountNotFound(
            ItemForAccountNotFoundException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleUsernameAlreadyExists(
            UsernameAlreadyExistsException ex,
            HttpServletRequest request){

        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleUsernameNotFound(
            UsernameNotFoundException ex,
            HttpServletRequest request){

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AccountIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleAccountIdNotFound(
            AccountIdNotFoundException ex,
            HttpServletRequest request){

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> handleUserNotAuthorized(
            UserNotAuthorizedException ex,
            HttpServletRequest request){

        ApiError error = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleAuthenticationFailed(
            AuthenticationFailedException ex,
            HttpServletRequest request){

        ApiError error = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}