package org.lamedh.pos.domain.sale;

import org.lamedh.common.domain.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService implements DomainService<Sale> {

    private SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale create() {
        Sale sale = new Sale();
        sale.setCode(generateCode());
        return sale;
    }

    @Override
    public Optional<Sale> getById(int id) {
        return saleRepository.getById(id);
    }

    @Override
    public Page<Sale> getAll(Pageable page) {
        return saleRepository.findAll(page);
    }

    @Override
    public Sale save(Sale sale) {
        if (sale.isTransient())
            sale.setCode(generateCode());
        sale.doGroupLineItems();
        return saleRepository.save(sale);
    }

    @Override
    public void delete(int id) {
        saleRepository.delete(id);
    }

    private String generateCode() {
        return "S" + (saleRepository.count() + 1);
    }
}
