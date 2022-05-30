package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.BreedModel;
import br.com.lacostech.pegasusbackend.services.BreedService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<BreedModel> insert(@RequestBody final BreedModel request) {
        BreedModel response = breedService.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
