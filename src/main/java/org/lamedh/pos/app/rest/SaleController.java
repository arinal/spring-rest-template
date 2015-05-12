package org.lamedh.pos.app.rest;

import org.lamedh.pos.app.rest.hateoas.SaleAssembler;
import org.lamedh.pos.app.rest.hateoas.SaleResource;
import org.lamedh.pos.common.app.rest.RestControllerBase;
import org.lamedh.pos.domain.Sale;
import org.lamedh.pos.domain.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController extends RestControllerBase<Sale, SaleService> {

    @Autowired
    public SaleController(SaleService service) {
        super(service, new SaleAssembler(), SaleResource::new);
    }
}
