package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.BreedModel;
import br.com.lacostech.pegasusbackend.services.BreedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/breeds")
@AllArgsConstructor
public class BreedController {

    private final BreedService breedService;

    @GetMapping
    public ResponseEntity<List<BreedModel>> findAll() {
        List<BreedModel> breeds = breedService.findAll();
        return ResponseEntity.ok(breeds);
    }

}
