package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.ProductController;
import org.lamedh.pos.common.app.rest.EntityAssembler;
import org.lamedh.pos.domain.Product;

public class ProductAssembler extends EntityAssembler<Product> {
    public ProductAssembler() {
        super(ProductController.class, ProductResource::new);
    }
}
