package com.example.expenseTrackerApi.exceptions;


//Custom exception class
public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
