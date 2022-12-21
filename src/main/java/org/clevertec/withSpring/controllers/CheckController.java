package org.clevertec.withSpring.controllers;

import jakarta.validation.Valid;
import org.clevertec.withSpring.entity.Product;
import org.clevertec.withSpring.servises.impl.CheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CheckController {
    
    @Autowired
    private CheckServiceImpl checkService;
    
    @PostMapping("/create")
    public ResponseEntity<Product> create(@Valid Product product) {
        return ResponseEntity.ok().body(checkService.create(product));
    }
    
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok().body(checkService.getAll());
    }
    
    @PostMapping("/update")
    public ResponseEntity<Optional<Product>> update(@RequestParam Double price, @RequestParam Long id) {
        checkService.update(price, id);
        return ResponseEntity.ok().body(checkService.getById(id));
    }
    
    @DeleteMapping
    public void delete (@RequestParam Long id) {
        checkService.delete(id);
    }
    
    @GetMapping("/check{query}")
    public ResponseEntity<String> printCheck (@RequestParam String query){
        return ResponseEntity.ok().body(checkService.printCheck(query));
    }
    
}
