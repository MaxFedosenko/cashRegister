package org.clevertec.withJavaCore;

import org.clevertec.withJavaCore.services.CheckService;
import org.clevertec.withJavaCore.services.impl.CheckServiceImpl;

public class Main {
    
    public static void main(String[] args) {
        CheckService checkService = new CheckServiceImpl();
        checkService.printCheck(args);
    }
}
