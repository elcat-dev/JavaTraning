package com.ertc.taskman.exceptions;

public class DirectoryIsEmptyException extends RuntimeException {
    public DirectoryIsEmptyException(String message) {
        super(message);
    }
}
