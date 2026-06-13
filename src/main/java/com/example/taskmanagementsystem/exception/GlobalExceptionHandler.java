package com.example.taskmanagementsystem.exception;

import com.example.taskmanagementsystem.dto.ErrorResponse;
import com.example.taskmanagementsystem.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handletasknotfound(TaskNotFoundException ex)
    {
        // ErrorResponse error=new ErrorResponse(
        //     ex.getMessage(),
        //     404
        // );
        return new ResponseEntity<>(//error
        new ApiResponse<>(false,ex.getMessage(),null)
        ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleusernotfound(UserNotFoundException ex)
    {
        // ErrorResponse error=new ErrorResponse(ex.getMessage(),404);
        return new ResponseEntity<>(new ApiResponse<>(false,ex.getMessage(),null),HttpStatus.NOT_FOUND);//this is standard api response
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerror(Exception ex)
    {
        log.error("Exception occured {}",ex.getMessage());
        ErrorResponse error=new ErrorResponse(ex.getMessage(),500);
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<HashMap<String,String>> handleValidation(MethodArgumentNotValidException ex)
   {
    HashMap<String,String> errors=new HashMap<>();
    ex.getBindingResult()
    .getFieldErrors()
    .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
   }
}