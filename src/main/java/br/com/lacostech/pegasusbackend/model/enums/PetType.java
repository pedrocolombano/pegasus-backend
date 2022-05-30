package br.com.lacostech.pegasusbackend.model.enums;

import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum PetType {

    CAT(1, "Cat"),
    DOG(2, "Dog"),
    BIRD(3, "Bird");

    private final Integer code;
    private final String description;

    public static PetType fromDescription(final String description) {
        return Stream
                .of(PetType.values())
                .filter(petType -> petType.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Pet type " + description + " not found"));
    }

}
