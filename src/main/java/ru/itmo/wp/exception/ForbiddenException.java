package ru.itmo.wp.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Forbidden exception");
    }
}
