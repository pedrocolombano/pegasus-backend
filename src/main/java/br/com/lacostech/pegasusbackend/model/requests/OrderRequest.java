package br.com.lacostech.pegasusbackend.model.requests;

import br.com.lacostech.pegasusbackend.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private OrderStatus status;
    private Set<OrderItemRequest> items = new HashSet<>();

}
