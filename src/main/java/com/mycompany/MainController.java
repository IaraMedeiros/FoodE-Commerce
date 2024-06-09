package com.mycompany;

import com.mycompany.entities.Category;
import com.mycompany.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    CategoryService categoService;

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

}
