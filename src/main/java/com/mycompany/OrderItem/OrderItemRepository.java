package com.mycompany.OrderItem;
import com.mycompany.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}