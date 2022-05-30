package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.responses.ScheduleResponse;
import br.com.lacostech.pegasusbackend.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{id}")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAll() {
        List<ScheduleResponse> schedules = scheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }

}
