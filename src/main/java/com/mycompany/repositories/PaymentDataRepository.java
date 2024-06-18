package com.mycompany.repositories;

import com.mycompany.entities.Order;
import com.mycompany.entities.PaymentData;
import org.springframework.data.repository.CrudRepository;

    public interface PaymentDataRepository extends CrudRepository<PaymentData, Integer> {
        public Long countById(Long id);
}
