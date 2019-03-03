package com.rodrigo.controllers;

import com.rodrigo.entities.Category;
import com.rodrigo.services.CategoryService;
import com.rodrigo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<Response<Category[]>> getAll() {
        return categoryService.getAll();
    }
}
