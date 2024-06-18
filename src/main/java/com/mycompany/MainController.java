package com.mycompany;

import com.mycompany.entities.*;
import com.mycompany.entities.enums.OrderStatus;
import com.mycompany.entities.enums.PaymentMethod;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.repositories.PaymentDataRepository;
import com.mycompany.repositories.ShippingRepository;
import com.mycompany.services.CategoryService;
import com.mycompany.services.PaymentService;
import com.mycompany.services.ShippingService;
import com.mycompany.services.OrderService;
import com.mycompany.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
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
    PaymentDataRepository paymentDataRepository;
    @Autowired
    private ShippingService shippingService;

    @Autowired
    private OrderService orderService;


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
    public String saveCheckout(@ModelAttribute("checkoutDTO") CheckoutDTO checkoutDTO, RedirectAttributes redirectAttributes,  HttpSession session) {
        // Extract and process the payment information from checkoutDTO
        Payment payment = new Payment();
        User user = checkoutDTO.getUser();
        Shipping shipping = checkoutDTO.getShipping();
        Order order = (Order) session.getAttribute("order");


        if (checkoutDTO.getPaymentMethod().equals("CARTAO_CREDITO") ||
                checkoutDTO.getPaymentMethod().equals("CARTAO_DEBITO") ||
                checkoutDTO.getPaymentMethod().equals("VOUCHER")) {
            CardData cardData = checkoutDTO.getCardData();
            paymentDataRepository.save((PaymentData) (cardData));
            payment.setPaymentData(cardData);
        } else if (checkoutDTO.getPaymentMethod().equals("PIX")) {
            PixData pixData = checkoutDTO.getPixData();
            paymentDataRepository.save((PaymentData) (pixData));
            payment.setPaymentData(pixData);
        }
        payment.setPaymentMethod(PaymentMethod.valueOf(checkoutDTO.getPaymentMethod()));
        shipping.setTimeExpected(Date.from(order.getMoment().plus(Duration.ofMinutes(30))));


        order.setCostumer(checkoutDTO.getUser());
        order.setShipping(checkoutDTO.getShipping());
        order.setPayment(payment);
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);



        payment.setOrder(order);
        user.getOrders().add(order);
        shipping.setOrder(order);
        shipping.setTimeExpected(Date.from(order.getMoment().plus(Duration.ofMinutes(30))));
        paymentService.save(payment);
        userService.save(checkoutDTO.getUser());
        shippingService.save(checkoutDTO.getShipping());

        orderService.save(order);


        return "redirect:/order-confirmation";
    }
}


/*@PostMapping("/checkout/save")
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
*/


