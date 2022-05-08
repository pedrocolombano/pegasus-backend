package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.responses.CategoryResponse;
import br.com.lacostech.pegasusbackend.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

}
