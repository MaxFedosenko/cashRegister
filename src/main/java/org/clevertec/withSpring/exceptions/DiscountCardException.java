package org.clevertec.withSpring.exceptions;

public class DiscountCardException extends CheckException{
    
    public DiscountCardException(String message) {
        super("Them discount card doesn't exist");
    }
}
