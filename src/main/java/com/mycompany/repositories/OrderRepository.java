package com.mycompany.repositories;

import com.mycompany.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    public Long countById(Integer id);

    @Query("SELECT o FROM Order o  WHERE o.orderStatus < 3 order by o.id")
    List<Order> AllActiveOrders();

    @Query("SELECT o FROM Order o  WHERE o.orderStatus = 3 order by o.id")
    List<Order> AllPastOrders();
}