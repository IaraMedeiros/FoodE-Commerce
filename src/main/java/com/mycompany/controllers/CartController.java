package com.mycompany.controllers;

import com.mycompany.entities.CartItem;
import com.mycompany.entities.Order;
import com.mycompany.entities.OrderItem;
import com.mycompany.entities.Product;
import com.mycompany.entities.enums.OrderStatus;
import com.mycompany.repositories.OrderItemRepository;
import com.mycompany.repositories.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cartItems")
public class CartController {
    @Autowired OrderItemRepository orderItemRepository;
    @Autowired OrderRepository orderRepository;
    @ModelAttribute("cartItems")
    public List<CartItem> getCartItems() {
        return new ArrayList<>();
    }

    @GetMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,
                            @RequestParam("product") Product product,
                            @RequestParam("unitPrice") double unitPrice,
                            @RequestParam("quantity") int quantity,
                            @ModelAttribute("cartItems") List<CartItem> cartItems) {
        CartItem newItem = new CartItem(product,quantity,unitPrice);
        cartItems.add(newItem);

        return "redirect:/cart";
    }

    @GetMapping("/remove-from-cart/{index}")
    public String removeFromCart(@PathVariable("index") int index,
                                 @ModelAttribute("cartItems") List<CartItem> cartItems) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cartItems") List<CartItem> cartItems, Model model) {
        model.addAttribute("cartItems", cartItems);
        double total = cartItems.stream().mapToDouble(item -> item.getQuantity() * item.getUnitPrice()).sum();
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout(@ModelAttribute("cartItems") List<CartItem> cartItems,  HttpSession session) {
         Order order = new Order(null);
         List<OrderItem> orderItems =  new ArrayList<>();


        for (CartItem ct: cartItems){
          OrderItem oi = new OrderItem(order,ct.getProduct(),ct.getQuantity(),ct.getUnitPrice());
          orderItems.add(oi);
          orderItemRepository.save(oi);
        }

        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        order.setCostumer(null);

        for(OrderItem oi: orderItems){
          order.getItems().add(oi);
        }

        order.setValor(order.getSubTotal());

        Order savedOrder = orderRepository.save(order);

        Order retrievedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);


        session.removeAttribute("cartItems");
        return "checkout";
    }
}

