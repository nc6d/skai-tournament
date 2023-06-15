package org.example.exception;

public class UnsupportedSportException extends RuntimeException {
    public UnsupportedSportException(String sportName, String fileName) {
        super("Sport " + sportName + " is not supported yet. Found in: " + fileName);
    }
}
