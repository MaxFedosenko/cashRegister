package org.clevertec.withSpring.exceptions;

public class FileException extends CheckException{
    
    public FileException(String message) {
        super("Them file doesn't exist");
    }
    
}
