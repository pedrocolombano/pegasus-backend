package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Post;
import br.com.lacostech.pegasusbackend.util.Randomizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDetailedResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String mainImageUrl;
    private String title;
    private String summary;
    private ThemeResponse theme;
    private final List<ArticleResponse> articles = new ArrayList<>();
    private final List<String> images = new ArrayList<>();

    public PostDetailedResponse(final Post entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);
            this.mainImageUrl = Randomizer.getRandomValueFromList(entity.getImages());
            this.theme = new ThemeResponse(entity.getTheme());

            entity.getArticles().forEach(art -> this.articles.add(new ArticleResponse(art)));
            this.images.addAll(entity.getImages());
        }
    }

}
