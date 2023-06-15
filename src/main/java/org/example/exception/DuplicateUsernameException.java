package org.example.exception;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String username, String fileName) {
        super("Duplicated username is stats file: " + username + ". Found in file: " + fileName);
    }
}
