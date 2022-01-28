package com.backend.csnotebook.exceptions;

/** Provides custom exception handling based on whether the user has access if not logged in. */
public class NotLoggedInException extends RuntimeException{
    /** Returns a message to indicate the user is trying to access a protected endpoint while
     * not logged in.
     * @param message The message indicating the user is not logged in while attempting to access a protected
     *                endpoint.*/
    public NotLoggedInException(String message) {
        super(message);
    }
}
