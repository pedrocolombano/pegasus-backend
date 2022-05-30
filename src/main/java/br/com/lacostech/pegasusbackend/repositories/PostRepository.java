package br.com.lacostech.pegasusbackend.repositories;

import br.com.lacostech.pegasusbackend.model.entities.Post;
import br.com.lacostech.pegasusbackend.model.entities.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByTheme(Theme theme, Pageable pageable);

}
