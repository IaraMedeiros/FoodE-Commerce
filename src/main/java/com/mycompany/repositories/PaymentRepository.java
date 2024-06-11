package com.mycompany.repositories;

import com.mycompany.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    public Long countById(Integer id);
}
