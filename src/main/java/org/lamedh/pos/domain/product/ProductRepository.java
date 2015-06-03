package org.lamedh.pos.domain.product;

import org.lamedh.common.domain.Repository;

import java.util.Optional;

public interface ProductRepository extends Repository<Product> {
    Optional<Product> getByCode(String code);
}

