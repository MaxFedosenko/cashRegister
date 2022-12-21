package org.clevertec.withSpring.servises.impl;

import org.clevertec.withSpring.entity.DiscountCard;
import org.clevertec.withSpring.entity.Product;
import org.clevertec.withSpring.exceptions.DiscountCardException;
import org.clevertec.withSpring.exceptions.ProductException;
import org.clevertec.withSpring.repositories.DiscountRepository;
import org.clevertec.withSpring.repositories.ProductRepository;
import org.clevertec.withSpring.servises.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CheckServiceImpl implements CheckService {
    
    private final String CAP_OF_CHECK = String.format("%-10s\n%-10s\n%-10s\n%-10s\n%-3s %-11s %-5s %s\n", "CASH RECEIPT", "SUPERMARKET 123", "12, MILKYWAY Galaxy/Earth", "Tel : 123-456-7890", "QTY", "DESCRIPTION", "PRICE", "TOTAL");
    
    @Autowired
    private ValidatorImpl validator;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private DiscountRepository discountRepository;
    
    
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    
    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }
    
    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public void update(Double price, Long id) {
        productRepository.update(price, id);
    }
    
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    
    @Override
    public String printCheck(String query) {
        String[] clientRequest = query.split(" ");
        String bodyOfCheck = generateCheck(clientRequest);
        return CAP_OF_CHECK + bodyOfCheck;
    }
    
    @Override
    public String generateCheck(String[] clientRequest) {
        DiscountCard discountCard = null;
        List<String> orders = new ArrayList<>();
        for (String order : clientRequest) {
            orders.add(order);
            if (order.contains("card")) {
                discountCard = discountRepository.findByName(order).orElseThrow(() -> new DiscountCardException("Them discount card doesn't exist"));
                orders.remove(discountCard.getName());
            }
        }
        StringBuilder check = new StringBuilder();
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        AtomicReference<Double> discount = new AtomicReference<>(0.0);
        for (String element : orders) {
            String[] split = element.split("-");
            Long id = Long.parseLong(split[0]);
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("Them product doesn't exist"));
            Integer quantity = Integer.parseInt(split[1]);
            Double price = product.getPrice();
            Double priceOfPosition = price * quantity;
            totalPrice.updateAndGet(v -> (v + priceOfPosition));
            discount.updateAndGet(v -> (v + validator.validationDiscountOfQuantity(quantity) * priceOfPosition));
            check.append(String.format("%-3s %-11s %-5s %s BYN\n", quantity, product.getName(), price, priceOfPosition));
        }
        validator.validationDiscount(discountCard, totalPrice, discount);
        double totalPriceWithDiscount = totalPrice.get() - discount.get();
        check.append(String.format("%-21s %s BYN\n%-21s %s BYN\n%-21s %s BYN", "PRICE", totalPrice, "DISCOUNT", discount, "TOTAL PRICE", totalPriceWithDiscount));
        return String.valueOf(check);
    }
}
