package org.lamedh.pos.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.lamedh.pos.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
