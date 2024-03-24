package com.mycompany.OrderItem;

import com.mycompany.order.Order;
import com.mycompany.order.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderItemController {
    @Autowired private OrderItemService service;

    @GetMapping(value="t/categories")
    public ResponseEntity<List<OrderItem>> findAll(){
        List<OrderItem> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "t/categories/{id}")
    public ResponseEntity<OrderItem> findById(@PathVariable Integer id) throws OrderItemNotFoundException {
        OrderItem orderItem = service.get(id);
        return ResponseEntity.ok().body(orderItem);
    }

}
