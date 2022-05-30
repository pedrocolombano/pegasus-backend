package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.entities.Article;
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
public class ArticleResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String subtitle;
    private final List<String> paragraphs = new ArrayList<>();

    public ArticleResponse(final Article entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);
            this.paragraphs.addAll(entity.getParagraphs());
        }
    }


}
