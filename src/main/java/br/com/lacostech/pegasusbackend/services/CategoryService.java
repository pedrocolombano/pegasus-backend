package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.CategoryModel;
import br.com.lacostech.pegasusbackend.model.entities.Category;
import br.com.lacostech.pegasusbackend.repositories.CategoryRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.DatabaseException;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional
    public CategoryModel update(final Long id, final CategoryModel request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id " + id + " not found"));
        copyDataFromRequest(request, category);
        category = categoryRepository.save(category);
        return new CategoryModel(category);
    }

    public void deleteById(final Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Category id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }

    private void copyDataFromRequest(final CategoryModel request, Category entity) {
        entity.setName(request.getName());
    }

}
