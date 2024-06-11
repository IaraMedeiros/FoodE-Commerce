package com.mycompany;

import com.mycompany.entities.Category;
import com.mycompany.entities.CheckoutDTO;
import com.mycompany.entities.Order;
import com.mycompany.entities.enums.OrderStatus;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.repositories.ShippingRepository;
import com.mycompany.services.CategoryService;
import com.mycompany.services.PaymentService;
import com.mycompany.services.ShippingService;
import com.mycompany.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    CategoryService categoService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;
    @Autowired
    private ShippingService shippingService;

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    @GetMapping("/menu")
    public String showMainMenu(Model model, @ModelAttribute("message") String message) {
        List<Category> listCategories = categoService.listAll();
        model.addAttribute("listCategories", listCategories);
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "menu";
    }

    @PostMapping("/checkout/save")
    public String processCheckout(@ModelAttribute CheckoutDTO checkoutDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        userService.save(checkoutDTO.getUser());
        shippingService.save(checkoutDTO.getShipping());
        paymentService.save(checkoutDTO.getPayment());


        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            checkoutDTO.getUser().getOrders().add(order);
            order.setCostumer(checkoutDTO.getUser());
            order.setPayment(checkoutDTO.getPayment());
            order.setShipping(checkoutDTO.getShipping());
            order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
            orderRepository.save(order);
        }

        return "redirect:/order-confirmation";
    }
}



