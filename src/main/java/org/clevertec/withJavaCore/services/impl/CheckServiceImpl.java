package org.clevertec.withJavaCore.services.impl;

import org.clevertec.withJavaCore.entity.Product;
import org.clevertec.withJavaCore.services.CheckService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CheckServiceImpl implements CheckService {
    
    private final String CAP_OF_CHECK = String.format("%-10s\n%-10s\n%-10s\n%-10s\n%-3s %-11s %-5s %s", "CASH RECEIPT", "SUPERMARKET 123", "12, MILKYWAY Galaxy/Earth", "Tel : 123-456-7890", "QTY", "DESCRIPTION", "PRICE", "TOTAL");
    
    private final List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "sausage", 3.0),
            new Product(2, "soda", 4.0),
            new Product(3, "cupcake", 2.0),
            new Product(4, "butter", 6.0),
            new Product(5, "bread", 1.0)));
    
    private final ValidatorImpl validator = new ValidatorImpl();
    
    
    public CheckServiceImpl() {
    }
    
    public void printCheck (String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\output.txt"))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                String read = reader.readLine();
                String[] s = read.split(" ");
                String[] capOfCheck = CAP_OF_CHECK.split("\n");
                for (String s1 : capOfCheck) {
                    writer.write(s1);
                    writer.newLine();
                }
                String[] split = generateCheck(s).split("\n");
                for (String s1 : split) {
                    writer.write(s1);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Them file doesn't exist");
        }
    }
    public String generateCheck(String[] clientRequest) {
        String card = null;
        List<String> order = new ArrayList<>();
        for (String s : clientRequest) {
            order.add(s);
            if (s.contains("card")) {
                card = s;
                order.remove(card);
            }
        }
        StringBuilder check = new StringBuilder();
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        AtomicReference<Double> discount = new AtomicReference<>(0.0);
        for (String element : order) {
            String[] split = element.split("-");
            int id = Integer.parseInt(split[0]) - 1;
            Product product = null;
            try {
                product = products.get(id);
            } catch (RuntimeException e) {
                System.out.println("Them product doesn't exist");
            }
            int quantity = Integer.parseInt(split[1]);
            assert product != null;
            double price = product.getPrice();
            double priceOfPosition = price * quantity;
            totalPrice.updateAndGet(v -> (v + priceOfPosition));
            discount.updateAndGet(v -> (v + validator.validateDiscountOfQuantity(quantity) * priceOfPosition));
            check.append(String.format("%-3s %-11s %-5s %s BYN\n", quantity, product.getName(), price, priceOfPosition));
        }
        validator.validateDiscount(card, totalPrice, discount);
        double totalPriceWithDiscount = totalPrice.get() - discount.get();
        check.append(String.format("%-21s %s BYN\n%-21s %s BYN\n%-21s %s BYN", "PRICE", totalPrice, "DISCOUNT", discount, "TOTAL PRICE", totalPriceWithDiscount));
        return String.valueOf(check);
    }
    
    
    
    
    
}
