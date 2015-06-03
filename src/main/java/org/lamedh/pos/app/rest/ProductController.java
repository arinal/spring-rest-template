package org.lamedh.pos.app.rest;

import org.lamedh.common.app.rest.RestControllerBase;
import org.lamedh.pos.domain.product.Product;
import org.lamedh.pos.domain.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/product")
class ProductController extends RestControllerBase<Product> {
    @Autowired
    ProductController(ProductService service) {
        super(service);
    }
}