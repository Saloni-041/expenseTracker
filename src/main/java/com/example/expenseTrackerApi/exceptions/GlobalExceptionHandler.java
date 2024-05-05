package com.example.expenseTrackerApi.exceptions;

import com.example.expenseTrackerApi.entity.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request)
    {
        ErrorObject object=new ErrorObject();
        object.setStatusCode(HttpStatus.NOT_FOUND.value());
        object.setMessage(ex.getMessage());
        object.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(object,HttpStatus.NOT_FOUND);
    }
}
