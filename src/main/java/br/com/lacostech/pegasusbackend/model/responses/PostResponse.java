package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Post;
import br.com.lacostech.pegasusbackend.util.Randomizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String mainImage;
    private String title;
    private String summary;
    private ThemeResponse theme;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponse(final Post entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);
            this.theme = new ThemeResponse(entity.getTheme());
            this.mainImage = Randomizer.getRandomValueFromList(entity.getImages());
        }
    }

}
