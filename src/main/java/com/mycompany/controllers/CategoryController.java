package com.mycompany.controllers;

import com.mycompany.entities.User;
import com.mycompany.exceptions.CategoryNotFoundException;
import com.mycompany.exceptions.UserNotFoundException;
import com.mycompany.services.CategoryService;
import com.mycompany.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired private CategoryService service;

    @GetMapping(value="t/categories")
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "t/categories/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) throws CategoryNotFoundException {
        Category category = service.get(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/categories")
    public String showCategoryList(Model model) {
        List<Category> listCategories = service.listAll();
        model.addAttribute("listCategories", listCategories);

        return "categories/categories";
    }

    @GetMapping("/categories/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Nova categoria");
        return "categories/category_form";
    }

    @PostMapping("/categories/save")
    public String saveUser(Category cat, RedirectAttributes ra) {
        service.save(cat);
        ra.addFlashAttribute("message", "The category has been saved successfully.");
        return "redirect:/categories";
    }

    @GetMapping("/category/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Category category = service.get(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Editar categoria (ID: " + id + ")");

            return "categories/category_form";
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The category ID " + id + " has been deleted.");
        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/categories";
    }

}