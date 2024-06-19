package com.mycompany.controllers;

import com.mycompany.entities.OrderItem;
import com.mycompany.entities.Product;
import com.mycompany.exceptions.OrderNotFoundException;
import com.mycompany.services.OrderService;
import com.mycompany.entities.Order;
import com.mycompany.entities.enums.OrderStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    @GetMapping("/order/change-status/{id}")
    public String changeOrderStatus(@PathVariable("id") Integer id, Model model, RedirectAttributes ra, HttpSession session) throws OrderNotFoundException {
        Order order = service.get(id);
        Integer OSCode = order.getOrderStatus().getCode();
        OSCode = OSCode + 1;
        order.setOrderStatus(OrderStatus.values()[(OSCode)]);

        service.save(order);

        model.addAttribute("order", order);
        return "redirect:/active-orders";
    }


    @GetMapping("/active-orders")
    public String showActiveOrders(Model model) {
        List<Order> listOrder = service.activeOrders();
        model.addAttribute("listOrder", listOrder);

        return "orders/orders";
    }

    @GetMapping("/past-orders")
    public String showPastOrders(Model model) {
        List<Order> listOrder = service.pastOrders();
        model.addAttribute("listOrder", listOrder);

        return "orders/pastOrders";
    }

}
