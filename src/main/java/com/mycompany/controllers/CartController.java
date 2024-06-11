package com.mycompany.controllers;

import com.mycompany.entities.*;
import com.mycompany.entities.enums.OrderStatus;
import com.mycompany.repositories.OrderItemRepository;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.repositories.ProductRepository;
import com.mycompany.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cartItems")
public class CartController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @ModelAttribute("cartItems")
    public List<CartItem> getCartItems() {
        return new ArrayList<>();
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable("productId") Integer productId,
                            @RequestParam("quantity") int quantity,
                            @ModelAttribute("cartItems") List<CartItem> cartItems,
                            HttpSession session) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            CartItem newItem = new CartItem(product, quantity, product.getPrice());
            cartItems.add(newItem);
        }

        return "redirect:/cart";
    }

    @GetMapping("/remove-from-cart/{index}")
    public String removeFromCart(@PathVariable("index") int index,
                                 @ModelAttribute("cartItems") List<CartItem> cartItems,
                                 HttpSession session) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cartItems") List<CartItem> cartItems, Model model, HttpSession session) {
        model.addAttribute("cartItems", cartItems);
        double total = cartItems.stream().mapToDouble(item -> item.getQuantity() * item.getUnitPrice()).sum();
        model.addAttribute("total", total);

        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout(@ModelAttribute("cartItems") List<CartItem> cartItems, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem ct : cartItems) {
            OrderItem oi = new OrderItem(order, ct.getProduct(), ct.getQuantity(), ct.getUnitPrice());
            orderItems.add(oi);
            System.out.println("Cart Items: " + ct);
            System.out.println("OrderItem: " + oi);
        }

        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        order.setCostumer(null);  // Replace with the actual customer

        for (OrderItem oi : orderItems) {
            order.getItems().add(oi);
        }

        order.setValor(order.getSubTotal());

        orderService.save(order);
        orderItemRepository.saveAll(orderItems);


        session.setAttribute("order", order);

        cartItems.clear();
        session.setAttribute("cartItems", cartItems);

        model.addAttribute("user", new User());
        model.addAttribute("order", order);
        model.addAttribute("checkoutDTO", new CheckoutDTO());

        return "checkout";
    }
}

