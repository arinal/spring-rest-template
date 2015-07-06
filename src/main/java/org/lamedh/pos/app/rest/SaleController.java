package org.lamedh.pos.app.rest;

import org.lamedh.common.app.rest.RestControllerBase;
import org.lamedh.pos.ApplicationConfig;
import org.lamedh.pos.domain.sale.Sale;
import org.lamedh.pos.domain.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/sale")
class SaleController extends RestControllerBase<Sale> {
    @Autowired
    public SaleController(SaleService service) {
        super(service);
    }
}

