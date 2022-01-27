package com.backend.csnotebook.exceptions;

/** Provides custom exception handling based on the presence of information. */
public class InfoExistsException extends RuntimeException{
    /** Returns custom message if information is found.
     * @param message The custom message to indicated information already exists.
     */
    public InfoExistsException(String message) {
        super(message);
    }
}
