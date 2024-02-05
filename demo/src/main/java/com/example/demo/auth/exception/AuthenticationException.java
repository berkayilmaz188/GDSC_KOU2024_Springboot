package com.example.demo.auth.exception;


import com.example.demo.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;


public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
