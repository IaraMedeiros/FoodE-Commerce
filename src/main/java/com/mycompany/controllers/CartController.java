/*package com.mycompany.controllers;

import com.mycompany.entities.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import javax.servlets.http.HttpSession;
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
        // Lógica para finalizar o pedido (conversão dos itens do carrinho em um pedido)
        // Salvar o pedido no banco de dados
        // Exemplo simplificado: limpar o carrinho (remover itens da sessão)
        session.removeAttribute("cartItems");
        return "checkout";
    }
}
 */