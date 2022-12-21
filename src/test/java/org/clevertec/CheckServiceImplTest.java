package org.clevertec;

import org.clevertec.withJavaCore.services.impl.CheckServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckServiceImplTest {
    
    private CheckServiceImpl service = new CheckServiceImpl();
    
    @Test
    public void testDiscountOfQuantity () {
        int v1 = 5;
        double expected = 0.1;
        double actual = service.discountOfQuantity(v1);
        Assertions.assertEquals(expected, actual);
    }
    
}
