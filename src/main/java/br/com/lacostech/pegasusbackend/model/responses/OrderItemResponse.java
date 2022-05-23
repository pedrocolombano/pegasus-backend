package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProductMinResponse product;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemResponse(OrderItem entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);

            this.product = new ProductMinResponse(entity.getProduct());
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = this.price.multiply(new BigDecimal(quantity));
        return subtotal.setScale(2, RoundingMode.HALF_EVEN);
    }

}
