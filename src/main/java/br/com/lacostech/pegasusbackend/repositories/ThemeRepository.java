package br.com.lacostech.pegasusbackend.repositories;

import br.com.lacostech.pegasusbackend.model.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
