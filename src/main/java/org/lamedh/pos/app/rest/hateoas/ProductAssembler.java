package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.EmployeeController;
import org.lamedh.pos.app.rest.ProductController;
import org.lamedh.pos.domain.Product;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

public class ProductAssembler extends ResourceAssemblerSupport<Product, Resource> {

    public ProductAssembler() {
        super(ProductController.class, Resource.class);
    }

    public List<Resource> toResources(Iterable<? extends Product> entities) {
        List<Resource> resources = new ArrayList<>();
        for (Product e:entities)
            resources.add(new Resource(e, new ProductResource(e).getLink("self")));
        return resources;
    }

    public Resource toResource(Product product) {
        return new Resource(product, ControllerLinkBuilder.linkTo(EmployeeController.class).slash(Integer.valueOf(product.getId())).withSelfRel());
    }
}
