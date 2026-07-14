package com.algaworks.algashop.authorizationserver.application.user.management;

public class AuthUserAlreadyExistsException extends RuntimeException {
    public AuthUserAlreadyExistsException(String message) {
        super(message);
    }
}
