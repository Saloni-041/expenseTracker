package com.example.expenseTrackerApi.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
