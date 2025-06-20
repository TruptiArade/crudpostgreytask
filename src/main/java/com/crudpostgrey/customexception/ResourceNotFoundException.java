package com.crudpostgrey.customexception;

public class ResourceNotFoundException  extends  RuntimeException{
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
