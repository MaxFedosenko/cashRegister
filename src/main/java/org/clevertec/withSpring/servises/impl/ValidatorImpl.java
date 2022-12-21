package org.clevertec.withSpring.servises.impl;

import org.clevertec.withSpring.entity.DiscountCard;
import org.clevertec.withSpring.servises.Validator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class ValidatorImpl implements Validator {
    @Override
    public void validationDiscount(DiscountCard card, AtomicReference<Double> totalPrice, AtomicReference<Double> discount) {
        if (card != null && totalPrice.get() >= 50) {
            discount.updateAndGet(v -> (v + totalPrice.get() * 0.01));
        }
    }
    
    @Override
    public double validationDiscountOfQuantity(Integer quantity) {
        if (quantity >= 5) {
            return 0.1;
        }
        return 0;
    }
}
