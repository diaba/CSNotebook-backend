package com.backend.csnotebook.model.auth.response;

/** The LoginResponse class maintains data and methods related to Log-in Response objects.
 */
public class LoginResponse {
    /** JSON Web Token Property */
    private String jwtToken;

    /** LoginResponse Constructor.
     * @param jwtToken the JSON web token.
     */
    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    /** Returns the JWT.
     * @return JSON Web Token.
     */
    public String getJwtToken() {
        return jwtToken;
    }


    /** Sets the JSON Web Token in the LoginResponse.
     * @param jwtToken the JWT to set in the response.
     */
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
