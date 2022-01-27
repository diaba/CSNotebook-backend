package com.backend.csnotebook.model.auth.request;

/** The LoginRequest class holds data and methods pertaining to log-in request objects used in user
 * authorization.
 */
public class LoginRequest {
    /** User's email provided as a part of the login request. */
    private String email;
    /** User's password provided as a part of the login request. */
    private String password;

    /** Returns the user's email for the login request.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /** Sets the email in the login request.
     * @param email The user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Returns the password in the login request.
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /** Sets the password in the login request
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
