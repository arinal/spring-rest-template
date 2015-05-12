package org.lamedh.pos.app.rest.domain.repo;

import org.lamedh.pos.app.rest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
