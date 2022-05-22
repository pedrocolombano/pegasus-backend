package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.CategoryModel;
import br.com.lacostech.pegasusbackend.model.entities.Category;
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

    @Transactional(readOnly = true)
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryModel::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryModel insert(final CategoryModel request) {
        Category category = new Category();
        copyDataFromRequest(request, category);
        category = categoryRepository.save(category);
        return new CategoryModel(category);
    }

    private void copyDataFromRequest(final CategoryModel request, Category entity) {
        entity.setName(request.getName());
    }

}
