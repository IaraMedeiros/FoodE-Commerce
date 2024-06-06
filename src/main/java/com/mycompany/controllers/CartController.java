/*package com.mycompany.controllers;

import com.mycompany.entities.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlets.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cartItems")
public class CartController {
    @ModelAttribute("cartItems")
    public List<CartItem> getCartItems() {
        return new ArrayList<>();
    }

    @GetMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,
                            @RequestParam("productName") String productName,
                            @RequestParam("unitPrice") double unitPrice,
                            @RequestParam("quantity") int quantity,
                            @ModelAttribute("cartItems") List<CartItem> cartItems) {
        CartItem newItem = new CartItem();
        newItem.setProductId(productId);
        newItem.setProductName(productName);
        newItem.setUnitPrice(unitPrice);
        newItem.setQuantity(quantity);

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
    public String checkout(@ModelAttribute("cartItems") List<CartItem> cartItems, HttpSession session) {
         Order order = new Order(null);
         List<OrderItem> orderItems = new List();


        For(CartItem ct: cartItems){
          OrderItem oi = new orderItem(TODAS AS VARIAVEIS DO CARTITEM)
          orderItems.add(oi);
          orderItemRepository.save(oi)
        }

        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        order.setCostumer(null);

        For(OrderItem oi: orderItems){
          order.getItems().add(oi);
        }

        order.setValor(order.getSubTotal());

        Order savedOrder = orderRepo.save(order);

        Order retrievedOrder = orderRepo.findById(savedOrder.getId()).orElse(null);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getId()).isGreaterThan(0);
        assertThat(savedOrder.getItems().size()).isEqualTo(2);

        session.removeAttribute("cartItems");
        return "checkout";
    }
}
*/
