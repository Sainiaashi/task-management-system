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
    @ExcepitonHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleusernotfound(UserNotFoundException ex)
    {
        ErrorResponse error=new ErrorResponse(ex.getMessage(),404);
        return new ResponseEntity<>(error,HttpStatus.Not_Found);
    }
    @ExpectionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerror(Exception ex)
    {
        ErrorResponse error=new ErrorResponse(ex.getMessage(),500);
        return new ResponseEntity<>(error,httpStatus.INTERNAL_SERVER_ERROR);
    }
}