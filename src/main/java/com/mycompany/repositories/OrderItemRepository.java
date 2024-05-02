package com.mycompany.repositories;
import com.mycompany.entities.OrderItem;
import com.mycompany.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}