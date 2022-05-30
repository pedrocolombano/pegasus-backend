package br.com.lacostech.pegasusbackend.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subtitle;
    private final List<String> paragraphs = new ArrayList<>();

}
