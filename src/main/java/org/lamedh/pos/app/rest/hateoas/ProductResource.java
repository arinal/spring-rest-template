package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.ProductController;
import org.lamedh.pos.common.app.rest.HateoasResource;
import org.lamedh.pos.domain.Product;

public class ProductResource extends HateoasResource<Product, ProductController> {
    public ProductResource(Product product) {
        super(product, new ProductController(null));
    }
}
