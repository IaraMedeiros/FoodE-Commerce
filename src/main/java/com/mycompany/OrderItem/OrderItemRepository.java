package com.mycompany.OrderItem;

import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    public Long countById(Integer id);
}