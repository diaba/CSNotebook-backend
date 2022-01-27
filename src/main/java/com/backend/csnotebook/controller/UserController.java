package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.User;
import com.backend.csnotebook.model.auth.request.LoginRequest;
import com.backend.csnotebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/** The UserController class serves as the controller for managing the flow of user data. */
@RestController
@RequestMapping("/auth/users")
public class UserController {

    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // CREATE NEW USER

    /** Creates a new user.
     * @param userObject The user object containing the necessary user data.
     * @return The newly created user.
     */
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        LOGGER.info("Calling createUser() method from UserController!");
        return userService.createUser(userObject);
    }

    // LOGIN USER

    /** Logs a user in and enables them to perform various requests.
     * @param loginRequest The login request containing necessary user information to authorize access.
     * @return JSON Web Token to permit user to execute requests.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LOGGER.info("Calling login() method from UserController!");
        return userService.loginUser(loginRequest);
    }
}
