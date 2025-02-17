package com.rr.file_wizard.exception;

public class ChecksumCalculationException extends RuntimeException {
    private ChecksumCalculationException(String message) {
        super(message);
    }
}
