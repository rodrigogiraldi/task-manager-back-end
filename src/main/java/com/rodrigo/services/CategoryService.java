package com.rodrigo.services;

import com.rodrigo.entities.Category;
import com.rodrigo.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public ResponseEntity<Response<Category[]>> getAll() {
        Category[] categories = Category.values();
        return new ResponseEntity<>(new Response<>(categories), HttpStatus.OK);
    }
}
