package org.lamedh.pos.app.rest.domain.repo;

import org.lamedh.pos.app.rest.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
