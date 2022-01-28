package com.backend.csnotebook.exceptions;

public class NotLoggedInException extends RuntimeException{
    public NotLoggedInException(String message) {
        super(message);
    }
}
