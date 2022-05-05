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
public class AddressRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;

}
