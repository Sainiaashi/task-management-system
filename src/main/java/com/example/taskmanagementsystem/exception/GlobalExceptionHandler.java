package com.example.taskmanagementsystem.exception;

import com.example.taskmanagementsystem.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handletasknotfound(TaskNotFoundException ex)
    {
        ErrorResponse error=new ErrorResponse(
            ex.getMessage(),
            404
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleusernotfound(UserNotFoundException ex)
    {
        ErrorResponse error=new ErrorResponse(ex.getMessage(),404);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerror(Exception ex)
    {
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