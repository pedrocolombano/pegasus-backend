package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.ProceedingModel;
import br.com.lacostech.pegasusbackend.services.ProceedingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
