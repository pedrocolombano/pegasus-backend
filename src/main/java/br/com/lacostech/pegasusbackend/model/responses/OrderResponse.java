package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Order;
import br.com.lacostech.pegasusbackend.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private OrderStatus status;
    private LocalDateTime moment;
    private LocalDateTime updatedAt;
    private Set<OrderItemResponse> items = new HashSet<>();
    private AddressResponse address;

    public OrderResponse(Order entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);

            this.address = new AddressResponse(entity.getAddress());

            entity.getItems().forEach(i -> this.items.add(new OrderItemResponse(i)));
        }
    }

    public String getStatus() {
        return this.status.getName();
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
