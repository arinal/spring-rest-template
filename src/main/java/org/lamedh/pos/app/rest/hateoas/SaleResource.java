package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.SaleController;
import org.lamedh.pos.common.app.rest.HateoasResource;
import org.lamedh.pos.domain.Sale;

public class SaleResource extends HateoasResource<Sale> {
    public SaleResource(Sale sale) {
        super(sale, SaleController.class);
    }
}
