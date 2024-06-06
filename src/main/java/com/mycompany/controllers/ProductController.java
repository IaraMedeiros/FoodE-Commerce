package com.mycompany.controllers;

import com.mycompany.entities.Category;
import com.mycompany.entities.Product;
import com.mycompany.entities.User;
import com.mycompany.exceptions.ProductNotFoundException;
import com.mycompany.services.CategoryService;
import com.mycompany.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;


@Controller
    public class ProductController {
        @Autowired ProductService service;
        @Autowired CategoryService categoService;

        // USER AREA
        @GetMapping(value="/menu")
        public String showMainMenu(Model model){
            List<Category> listCategories = categoService.listAll();
            model.addAttribute("listCategories", listCategories);

            return "products/products";
        }

    @GetMapping("/product/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Integer id) throws ProductNotFoundException {
        Product product = service.get(id);

        byte[] imgBytes = product.getImg();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imgBytes);

    }


    // DASHBOARD AREA
    @GetMapping("/products/new")
    public String showNewFormProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Adicionar novo produto");
        return "dashboard/product_form";
    }
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product, HttpServletRequest request) {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        if (file != null && !file.isEmpty()) {
            try {
                product.setImg(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        service.save(product);
        return "redirect:/products";
    }
    @GetMapping("product/delete/{id}")
        public String deleteProduct(@PathVariable Integer id, RedirectAttributes ra ){
            try {
                service.delete(id);
            }catch (ProductNotFoundException e){
                ra.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/products";
        }

    @GetMapping("/product/edit/{id}")
    public String showEditFormProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Editar Produto");

            return "dashboard/product_form";
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/product";
        }
    }

    @GetMapping("/products")
    public String showProductList(Model model) {
        List<Product> listProduct = service.listAll();
        model.addAttribute("listProducts", listProduct);

        return "dashboard/products";
    }

    @GetMapping(value="t/products")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }


}
