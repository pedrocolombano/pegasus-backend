package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class ProductDetailedResponse extends ProductMinResponse {

    private static final long serialVersionUID = 1L;

    private String description;

    public ProductDetailedResponse() {
        super();
    }

    public ProductDetailedResponse(Long id, String name, BigDecimal price, String imageUrl, String description) {
        super(id, name, price, imageUrl);
        this.description = description;
    }

    public ProductDetailedResponse(Product entity) {
        super(entity);
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);
        }
    }

}
