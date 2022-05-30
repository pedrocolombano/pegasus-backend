package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.ThemeModel;
import br.com.lacostech.pegasusbackend.services.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/themes")
@AllArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping
    public ResponseEntity<List<ThemeModel>> findAll() {
        List<ThemeModel> themes = themeService.findAll();
        return ResponseEntity.ok(themes);
    }

}
