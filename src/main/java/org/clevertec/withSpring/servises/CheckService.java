package org.clevertec.withSpring.servises;

import org.clevertec.withSpring.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CheckService {
    
    List<Product> getAll();
    Optional<Product> getById(Long id);
    Product create(Product product);
    void update(Double price, Long id);
    void delete(Long id);
    String printCheck (String query);
    String generateCheck(String[] clientRequest);
    
}
