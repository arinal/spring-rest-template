package org.lamedh.pos.domain.employee;

import org.lamedh.common.domain.Repository;

import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee> {
    Optional<Employee> getByCode(String code);
}
