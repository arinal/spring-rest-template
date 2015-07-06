package org.lamedh.pos.domain.sale;

import org.lamedh.common.domain.Repository;
import org.lamedh.pos.domain.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SaleRepository extends Repository<Sale> {
    @Query("from Sale e where e.cashier.name like ?1%")
    Page<Sale> findAll(String search, Pageable page);
}
