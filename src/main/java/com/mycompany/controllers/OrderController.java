package com.mycompany.controllers;

import com.mycompany.entities.Product;
import com.mycompany.exceptions.OrderNotFoundException;
import com.mycompany.services.OrderService;
import com.mycompany.entities.Order;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;


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

    @GetMapping("/order-confirmation")
    public String showOrderConfirmation(Model model, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            return "redirect:/menu"; // Redirect to menu if no order is found in session
        }
        model.addAttribute("order", order);
        return "orderConfirmation";
    }

    @GetMapping("/active-orders")
    public String showActiveOrders(Model model) {
        List<Order> listOrder = service.activeOrders();
        model.addAttribute("listOrder", listOrder);

        return "orders/orders";
    }

}
