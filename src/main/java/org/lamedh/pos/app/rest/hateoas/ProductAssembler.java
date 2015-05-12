package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.ProductController;
import org.lamedh.pos.common.app.rest.HateoasAssembler;
import org.lamedh.pos.domain.Product;

public class ProductAssembler extends HateoasAssembler<Product> {
    public ProductAssembler() {
        super(ProductController.class, ProductResource::new);
    }
}
