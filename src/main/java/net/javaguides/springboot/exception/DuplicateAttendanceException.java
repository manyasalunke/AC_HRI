package net.javaguides.springboot.exception;

public class DuplicateAttendanceException extends RuntimeException {
    public DuplicateAttendanceException(String message) {
        super(message);
    }
}