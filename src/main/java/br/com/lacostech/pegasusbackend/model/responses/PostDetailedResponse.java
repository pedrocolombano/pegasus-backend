package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PostDetailedResponse extends PostResponse {

    private final List<ArticleResponse> articles = new ArrayList<>();
    private final List<String> images = new ArrayList<>();

    public PostDetailedResponse(final Post entity) {
        super(entity);
        if (Objects.nonNull(entity)) {
            entity.getArticles().forEach(art -> this.articles.add(new ArticleResponse(art)));
            this.images.addAll(entity.getImages());
        }
    }

}
