package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Theme;
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
public class ThemeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public ThemeResponse(final Theme entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);
        }
    }

}
