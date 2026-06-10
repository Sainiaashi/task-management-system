package com.example.taskmanagementsystem.exception;

import com.example.taskmanagementsystem.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}