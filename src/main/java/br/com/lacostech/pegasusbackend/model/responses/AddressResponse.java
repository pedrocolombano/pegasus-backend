package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;

    public AddressResponse(final Address entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);
        }
    }

}
