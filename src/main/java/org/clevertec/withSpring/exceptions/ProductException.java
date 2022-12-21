package org.clevertec.withSpring.exceptions;

public class ProductException extends CheckException{
    
    public ProductException(String message) {
        super("Them product doesn't exist");
    }
    
}
