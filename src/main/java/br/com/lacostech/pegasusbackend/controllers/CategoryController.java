package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.CategoryModel;
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
    public ResponseEntity<List<CategoryModel>> findAll() {
        List<CategoryModel> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

}
