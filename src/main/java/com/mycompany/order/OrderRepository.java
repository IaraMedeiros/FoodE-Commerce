package com.mycompany.order;

import com.mycompany.category.Category;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    public Long countById(Integer id);
}