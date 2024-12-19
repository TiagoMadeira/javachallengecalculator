package com.example.exceptions;

public class OperationDoesNotExistException extends Exception{

    public OperationDoesNotExistException(String operation){
        super("Operation does not exist!" + operation);
    }
}
