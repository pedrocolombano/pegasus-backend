package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.responses.CategoryResponse;
import br.com.lacostech.pegasusbackend.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

}
