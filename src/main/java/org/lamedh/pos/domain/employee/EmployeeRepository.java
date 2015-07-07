package org.lamedh.pos.domain.employee;

import org.lamedh.common.domain.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee> {
    Optional<Employee> getByCode(String code);

    @Query("from Employee e where e.name like ?1% or e.code like ?1%")
    Page<Employee> findAll(String search, Pageable page);
}
