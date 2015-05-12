package org.lamedh.pos.app.rest;

import org.lamedh.pos.app.rest.hateoas.ProductAssembler;
import org.lamedh.pos.app.rest.hateoas.ProductResource;
import org.lamedh.pos.common.app.rest.RestControllerBase;
import org.lamedh.pos.domain.Product;
import org.lamedh.pos.domain.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController extends RestControllerBase<Product, ProductService, ProductResource> {
    @Autowired
    public ProductController(ProductService service) {
        super(service, new ProductAssembler());
    }

    @Override
    protected ProductResource createResource(Product product) {
        return new ProductResource(product);
    }
}
