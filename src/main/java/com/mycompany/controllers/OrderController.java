package com.mycompany.controllers;

import com.mycompany.exceptions.OrderNotFoundException;
import com.mycompany.services.OrderService;
import com.mycompany.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    @Autowired private OrderService service;

    @GetMapping(value="t/orders")
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "t/orders/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) throws OrderNotFoundException {
        Order order = service.get(id);
        return ResponseEntity.ok().body(order);
    }

}
