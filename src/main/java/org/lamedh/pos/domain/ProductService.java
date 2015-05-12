package org.lamedh.pos.domain;

import org.lamedh.pos.common.domain.DomainService;
import org.lamedh.pos.domain.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements DomainService<Product> {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create() {
        Product product = new Product();
        product.setCode(generateCode());
        return product;
    }

    public Product save(Product product) {
        product.setCode(generateCode());
        return repository.save(product);
    }

    public Optional<Product> getById(int id) {
        return repository.getById(id);
    }

    public Page<Product> getAll(Pageable page) {
        return repository.findAll(page);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    private String generateCode() {
        return "P" + (repository.count() + 1);
    }
}
