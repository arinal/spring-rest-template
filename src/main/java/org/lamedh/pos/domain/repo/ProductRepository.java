package org.lamedh.pos.domain.repo;

import org.lamedh.pos.common.domain.Repository;
import org.lamedh.pos.domain.Product;

import java.util.Optional;

public interface ProductRepository extends Repository<Product> {
    Optional<Product> getByCode(String code);
}

