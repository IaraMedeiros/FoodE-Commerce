package com.mycompany.category;

import com.mycompany.user.User;
import com.mycompany.user.UserNotFoundException;
import com.mycompany.user.UserService;
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

}