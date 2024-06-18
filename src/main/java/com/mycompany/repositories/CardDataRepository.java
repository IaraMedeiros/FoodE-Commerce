package com.mycompany.repositories;

import com.mycompany.entities.CardData;
import com.mycompany.entities.PaymentData;
import org.springframework.data.repository.CrudRepository;

public interface CardDataRepository extends CrudRepository<CardData, Integer> {
    public Long countById(Long id);
}