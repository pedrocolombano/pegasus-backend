package br.com.lacostech.pegasusbackend.model.requests;

import br.com.lacostech.pegasusbackend.model.RoleModel;
import br.com.lacostech.pegasusbackend.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String password;
    private LocalDate birthDate;
    private Gender gender;
    private Set<RoleModel> roles = new HashSet<>();

}
