package com.example.expenseTrackerApi.exceptions;

import com.example.expenseTrackerApi.entity.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//It internally uses component annotation as soon as app runs spring will create object of this class
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleException(MethodArgumentTypeMismatchException ex,WebRequest request)
    {
        ErrorObject errorObject=new ErrorObject();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex,WebRequest request)
    {
        ErrorObject errorObject=new ErrorObject();
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    When a request with invalid arguments (as per the validation annotations) is received, this method constructs a detailed error response
//    This exception occurs when validation on an argument annotated with @Valid fails
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> map=new HashMap<>();
        map.put("timestamp",new Date());
        map.put("statusCode",status.value());
        List<String> errors=ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        map.put("errors",errors);
        return new ResponseEntity<>(map,status);
    }
}
