package org.clevertec.withSpring.repositories;

import org.clevertec.withSpring.entity.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<DiscountCard, Long> {
    
    Optional<DiscountCard> findByName(String name);
    
}
