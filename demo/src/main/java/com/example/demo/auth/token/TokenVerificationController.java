package com.example.demo.auth.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TokenVerificationController {

    private final TokenService tokenService;

    @Autowired
    public TokenVerificationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/verifyToken")
    public ResponseEntity<String> verifyToken(@RequestParam String token) {
        boolean isValid = tokenService.verifyToken(token);
        return ResponseEntity.ok("Token is valid: " + isValid);
    }
}
