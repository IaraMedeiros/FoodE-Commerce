package com.mycompany.controllers;

import com.mycompany.entities.Order;
import com.mycompany.entities.Shipping;
import com.mycompany.services.OrderService;
import com.mycompany.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShippingController {

    @Autowired private ShippingService  service;

    @GetMapping("/shippings")
    public String showShippings(Model model) {
        List<Shipping> listShipping = service.pastShippings();
        model.addAttribute("listShipping", listShipping);

        return "shippings/shippings";
    }
}
