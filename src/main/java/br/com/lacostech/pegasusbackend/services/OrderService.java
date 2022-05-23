package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Order;
import br.com.lacostech.pegasusbackend.model.entities.OrderItem;
import br.com.lacostech.pegasusbackend.model.entities.Product;
import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.enums.OrderStatus;
import br.com.lacostech.pegasusbackend.model.requests.OrderRequest;
import br.com.lacostech.pegasusbackend.model.responses.OrderResponse;
import br.com.lacostech.pegasusbackend.repositories.OrderItemRepository;
import br.com.lacostech.pegasusbackend.repositories.OrderRepository;
import br.com.lacostech.pegasusbackend.repositories.ProductRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        User client = authService.getAuthenticatedUser();
        return orderRepository.findAllByClient(client)
                .stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse insert(final OrderRequest request) {
        Order order = new Order();
        copyDataFromRequest(request, order);
        order.setStatus(OrderStatus.PAID);

        order = orderRepository.save(order);
        setOrderItemDataFromRequest(request, order);

        return new OrderResponse(order);
    }

    private void copyDataFromRequest(final OrderRequest request, final Order order) {
        if (Objects.nonNull(request)) {
            BeanUtils.copyProperties(request, order);

            User client = authService.getAuthenticatedUser();
            order.setClient(client);
        }
    }

    private void setOrderItemDataFromRequest(final OrderRequest request, final Order order) {
        request.getItems().forEach(i -> {
            OrderItem item = new OrderItem();
            BeanUtils.copyProperties(i, item);

            item.setOrder(order);

            Product product = productRepository.findById(i.getProductId())
                            .orElseThrow(() -> new NotFoundException("Product id " + i.getProductId() + " not found"));
            item.setProduct(product);
            item.setPrice(product.getPrice());

            item = orderItemRepository.save(item);
            order.getItems().add(item);
        });
    }

}
