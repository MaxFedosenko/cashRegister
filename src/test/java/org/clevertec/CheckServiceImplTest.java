package org.clevertec;

import org.clevertec.withJavaCore.services.CheckService;
import org.clevertec.withJavaCore.services.Validator;
import org.clevertec.withJavaCore.services.impl.CheckServiceImpl;
import org.clevertec.withJavaCore.services.impl.ValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckServiceImplTest {
    
    private CheckService service = new CheckServiceImpl();
    private Validator validator = new ValidatorImpl();
    
    @Test
    public void testDiscountOfQuantity () {
        int v1 = 5;
        double expected = 0.1;
        double actual = validator.validateDiscountOfQuantity(v1);
        Assertions.assertEquals(expected, actual);
    }
    
}
