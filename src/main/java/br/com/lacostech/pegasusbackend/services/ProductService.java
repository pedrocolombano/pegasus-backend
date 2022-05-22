package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Category;
import br.com.lacostech.pegasusbackend.model.entities.Product;
import br.com.lacostech.pegasusbackend.model.requests.ProductRequest;
import br.com.lacostech.pegasusbackend.model.responses.ProductDetailedResponse;
import br.com.lacostech.pegasusbackend.model.responses.ProductMinResponse;
import br.com.lacostech.pegasusbackend.repositories.CategoryRepository;
import br.com.lacostech.pegasusbackend.repositories.ProductRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductMinResponse> findAllPaged(final Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMinResponse::new);
    }

    @Transactional(readOnly = true)
    public ProductDetailedResponse findById(final Long id) {
        Product product = getProductById(id);
        return new ProductDetailedResponse(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinResponse> findAllByName(final String search, final Pageable pageable) {
        return productRepository.findAllByNameContainingIgnoreCase(search, pageable)
                .map(ProductMinResponse::new);
    }

    @Transactional
    public ProductDetailedResponse insert(final ProductRequest request) {
        Product product = new Product();
        copyDataFromRequest(request, product);
        product = productRepository.save(product);
        return new ProductDetailedResponse(product);
    }

    @Transactional
    public ProductDetailedResponse update(final Long id, final ProductRequest request) {
        Product product = getProductById(id);
        copyDataFromRequest(request, product);
        product = productRepository.save(product);
        return new ProductDetailedResponse(product);
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product id " + id + " not found"));
    }

    private void copyDataFromRequest(final ProductRequest request, Product entity) {
        if (Objects.nonNull(request)) {
            BeanUtils.copyProperties(request, entity);

            entity.getCategories().clear();
            request.getCategories().forEach(cat -> {
                Category category = categoryRepository.findById(cat.getId())
                        .orElseThrow(() -> new NotFoundException("Category id " + cat.getId() + " not found"));
                entity.getCategories().add(category);
            });
        }
    }

}
