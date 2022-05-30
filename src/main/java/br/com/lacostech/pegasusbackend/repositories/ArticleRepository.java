package br.com.lacostech.pegasusbackend.repositories;

import br.com.lacostech.pegasusbackend.model.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
