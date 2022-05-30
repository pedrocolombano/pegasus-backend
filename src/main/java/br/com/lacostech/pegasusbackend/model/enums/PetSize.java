package br.com.lacostech.pegasusbackend.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetSize {

    SMALL(1, "Small"),
    MEDIUM(2, "Medium"),
    BIG(3, "Big");

    private final Integer code;
    private final String description;

}
