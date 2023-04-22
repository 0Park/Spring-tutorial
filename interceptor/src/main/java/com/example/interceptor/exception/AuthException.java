package com.example.interceptor.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthException extends RuntimeException{
    String message;
}
