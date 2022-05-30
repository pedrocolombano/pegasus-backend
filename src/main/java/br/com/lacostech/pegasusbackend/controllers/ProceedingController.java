package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.ProceedingModel;
import br.com.lacostech.pegasusbackend.services.ProceedingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proceedings")
@AllArgsConstructor
public class ProceedingController {

    private final ProceedingService proceedingService;

    @GetMapping
    public ResponseEntity<List<ProceedingModel>> findAll() {
        List<ProceedingModel> proceedings = proceedingService.findAll();
        return ResponseEntity.ok(proceedings);
    }

    @PostMapping
    public ResponseEntity<ProceedingModel> insert(@RequestBody final ProceedingModel request) {
        ProceedingModel response = proceedingService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProceedingModel> update(
            @PathVariable final Long id,
            @RequestBody final ProceedingModel request) {
        ProceedingModel response = proceedingService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        proceedingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
