package com.mycompany.controllers;


import com.mycompany.repositories.OrderRepository;
import com.mycompany.entities.Order;
import com.mycompany.entities.User;
import com.mycompany.entities.enums.OrderStatus;
import com.mycompany.exceptions.UserNotFoundException;
import com.mycompany.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;
    @Autowired private OrderRepository orderRepository;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users/users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "users/user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "users/user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/checkout/details")
    public String showCheckoutForm(Model model, HttpSession session, @ModelAttribute("order") Order order) {
        if (order == null) {
            order = (Order) session.getAttribute("order");
        }

        model.addAttribute("user", new User());
        model.addAttribute("order", order);

        return "/checkout";
    }

    @PostMapping("/checkout/save")
    public String processCheckout(User user, HttpSession session, RedirectAttributes redirectAttributes) {
        service.save(user);

        // Retrieve the order from the session
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            order.setCostumer(user);
            order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
            orderRepository.save(order);
        }

        return "redirect:/order-confirmation";
    }
}

