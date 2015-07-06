package org.lamedh.pos.domain.sale;

import org.lamedh.common.domain.DomainServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends DomainServiceBase<Sale> {

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        super(Sale.class, saleRepository);
    }

    @Override
    public Sale create() {
        Sale sale = new Sale();
        sale.setCode(generateCode());
        return sale;
    }

    @Override
    public Sale save(Sale sale) {
        if (sale.isTransient())
            sale.setCode(generateCode());
        sale.doGroupLineItems();
        return repository.save(sale);
    }

    private String generateCode() {
        return "S" + (repository.count() + 1);
    }
}
