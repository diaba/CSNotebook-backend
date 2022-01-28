package com.backend.csnotebook.exceptions;

/** Provides custom exception handling in the event a user attempts to change restricted content */
public class RestrictedAccessException extends RuntimeException{

    /** Provides a custom message alerting that the user may not alter targeted content (topic / card).
     * @param message */
    public RestrictedAccessException(String message) {
        super(message);
    }
}
