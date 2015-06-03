package org.lamedh.pos.domain.product;

import org.lamedh.common.domain.DomainServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends DomainServiceBase<Product> {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        super(Product.class, repository);
        this.repository = repository;
    }

    public Product create() {
        Product product = new Product();
        product.setCode(generateCode());
        return product;
    }

    public Product save(Product product) {
        if (product.isTransient())
            product.setCode(generateCode());
        return repository.save(product);
    }

    private String generateCode() {
        return "P" + (repository.count() + 1);
    }
}
