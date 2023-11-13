package com.app.registration.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RegistrationServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> errorMap = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException){
        Map<String, String> errorMap = new HashMap<>();
        String errMsg = dataIntegrityViolationException.getMessage();
        String errMsgSlice = errMsg.substring(errMsg.indexOf('[')+1,errMsg.indexOf(']'));
        errorMap.put("error", "Data integrity violation");
        errorMap.put("msg",errMsgSlice);
        errorMap.put("timestamp", LocalDateTime.now().toString());
        return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.CONFLICT);
    }
}
