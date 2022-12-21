package org.clevertec.withSpring.servises;

import org.clevertec.withSpring.entity.DiscountCard;

import java.util.concurrent.atomic.AtomicReference;

public interface Validator {
    
    void validationDiscount(DiscountCard card, AtomicReference<Double> totalPrice, AtomicReference<Double> discount);
    
    double validationDiscountOfQuantity(Integer quantity);
    
}
