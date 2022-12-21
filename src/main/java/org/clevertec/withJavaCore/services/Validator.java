package org.clevertec.withJavaCore.services;

import java.util.concurrent.atomic.AtomicReference;

public interface Validator {
    
    void validateDiscount(String card, AtomicReference<Double> totalPrice, AtomicReference<Double> discount);
    
    double validateDiscountOfQuantity(int quantity);
    
}
