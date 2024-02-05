package com.example.demo.auth;


import com.example.demo.auth.dto.Credentials;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import com.example.demo.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService; // Initialize UserService
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Credentials credentials) {
        String token = authService.authenticate(credentials.getEmail(), credentials.getPassword());
        User user = userService.findByEmail(credentials.getEmail()); // Make sure this method is public in UserService
        UserDTO userDTO = new UserDTO(user);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("Bearer Token", token);
        response.put("userData", userDTO);

        return ResponseEntity.ok(response);
    }

}

