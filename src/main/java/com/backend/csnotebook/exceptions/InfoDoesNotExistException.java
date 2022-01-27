package com.backend.csnotebook.exceptions;

/** Provides custom exception handling based on the non-existence of information. */
public class InfoDoesNotExistException extends RuntimeException {
    /** Returns custom message if information is not found.
     * @param message The custom message to indicated information doesn't exist.
     */
    public InfoDoesNotExistException(String message) {
        super(message);
    }
}
