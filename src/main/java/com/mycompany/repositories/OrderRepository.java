package com.mycompany.repositories;

import com.mycompany.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    public Long countById(Integer id);
}