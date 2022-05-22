package br.com.lacostech.pegasusbackend.controllers;

import br.com.lacostech.pegasusbackend.model.requests.ProductRequest;
import br.com.lacostech.pegasusbackend.model.responses.ProductDetailedResponse;
import br.com.lacostech.pegasusbackend.model.responses.ProductMinResponse;
import br.com.lacostech.pegasusbackend.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductMinResponse>> findAllPaged(@PageableDefault(size = 20) final Pageable pageable) {
        Page<ProductMinResponse> products = productService.findAllPaged(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailedResponse> findById(@PathVariable Long id) {
        ProductDetailedResponse product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductMinResponse>> findAllByName(@RequestParam String query,
                                                                  @PageableDefault(size = 20) final Pageable pageable) {
        Page<ProductMinResponse> products = productService.findAllByName(query, pageable);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDetailedResponse> insert(@RequestBody final ProductRequest request) {
        ProductDetailedResponse response = productService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailedResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductDetailedResponse response = productService.update(id, request);
        return ResponseEntity.ok(response);
    }

}
