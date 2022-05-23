package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.responses.OrderResponse;
import br.com.lacostech.pegasusbackend.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        User client = authService.getAuthenticatedUser();
        return orderRepository.findAllByClient(client)
                .stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

}
