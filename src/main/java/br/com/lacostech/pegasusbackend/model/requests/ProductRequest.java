package br.com.lacostech.pegasusbackend.model.requests;

import br.com.lacostech.pegasusbackend.model.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private Set<CategoryModel> categories = new HashSet<>();

}
