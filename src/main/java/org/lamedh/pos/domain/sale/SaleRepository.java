package org.lamedh.pos.domain.sale;

import org.lamedh.common.domain.Repository;

import java.util.Optional;

public interface SaleRepository extends Repository<Sale> {
    Optional<Sale> getByCode(String code);
}
