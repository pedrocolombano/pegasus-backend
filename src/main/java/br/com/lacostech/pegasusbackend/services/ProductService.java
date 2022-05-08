package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Product;
import br.com.lacostech.pegasusbackend.model.responses.ProductDetailedResponse;
import br.com.lacostech.pegasusbackend.model.responses.ProductMinResponse;
import br.com.lacostech.pegasusbackend.repositories.ProductRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductMinResponse> findAllPaged(final Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMinResponse::new);
    }

    @Transactional(readOnly = true)
    public ProductDetailedResponse findById(final Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product id " + id + " not found"));
        return new ProductDetailedResponse(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinResponse> findAllByName(final String search, final Pageable pageable) {
        return productRepository.findAllByNameContainingIgnoreCase(search, pageable)
                .map(ProductMinResponse::new);
    }

}
