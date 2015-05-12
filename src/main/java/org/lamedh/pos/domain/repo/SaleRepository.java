package org.lamedh.pos.domain.repo;

import org.lamedh.pos.common.domain.Repository;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends Repository<Sale> {
    Optional<Sale> getByCode(String code);
}
