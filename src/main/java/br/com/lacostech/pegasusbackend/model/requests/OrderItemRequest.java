package br.com.lacostech.pegasusbackend.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productId;
    private Integer quantity;

}
