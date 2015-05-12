package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.SaleController;
import org.lamedh.pos.common.app.rest.HateoasAssembler;
import org.lamedh.pos.domain.Sale;

public class SaleAssembler extends HateoasAssembler<Sale> {
    public SaleAssembler() {
        super(SaleController.class, SaleResource::new);
    }
}
