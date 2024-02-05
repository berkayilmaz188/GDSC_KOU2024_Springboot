package com.example.demo.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.auth.exception.AuthenticationException;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ISSUER = "gdsckou2024";
    private final String SECRET_KEY = "test-secret-key";

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        if (SECRET_KEY == null) {
            throw new IllegalArgumentException("The Secret key is not defined in environment variables.");
        }
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateToken(user);
        } else {
            throw new AuthenticationException("Invalid email or password.");
        }
    }

    private String generateToken(User user) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // 1 day expiration

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}

