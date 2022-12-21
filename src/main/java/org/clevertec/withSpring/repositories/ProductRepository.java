package org.clevertec.withSpring.repositories;

import org.clevertec.withSpring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Transactional
    @Modifying
    @Query("update Product set price = :price where id = :id")
    void update(@Param("price") Double price, @Param("id") Long id);

}
