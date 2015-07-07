package org.lamedh.pos.domain.product;

import org.lamedh.common.domain.Repository;
import org.lamedh.pos.domain.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends Repository<Product> {
    @Query("from Product p where p.code like ?1% or p.name like ?1%")
    Page<Product> findAll(String search, Pageable page);
}

