package org.clevertec.withJavaCore.services.impl;

import org.clevertec.withJavaCore.services.Validator;

import java.util.concurrent.atomic.AtomicReference;

public class ValidatorImpl implements Validator {
    @Override
    public void validateDiscount(String card, AtomicReference<Double> totalPrice, AtomicReference<Double> discount) {
        if (card != null && totalPrice.get() >= 50) {
            discount.updateAndGet(v -> (v + totalPrice.get() * 0.01));
        }
    }
    
    @Override
    public double validateDiscountOfQuantity(int quantity) {
        if (quantity >= 5) {
            return 0.1;
        }
        return 0;
    }
}
