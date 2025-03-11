package com.rr.file_wizard.infrastructure.exception;

public class ChecksumCalculationException extends RuntimeException {
    private ChecksumCalculationException(String message) {
        super(message);
    }
}
