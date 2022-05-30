package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.responses.ScheduleResponse;
import br.com.lacostech.pegasusbackend.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAll() {
        User client = authService.getAuthenticatedUser();
        return scheduleRepository
                .findAllByClient(client)
                .stream()
                .map(ScheduleResponse::new)
                .collect(Collectors.toList());
    }

}
