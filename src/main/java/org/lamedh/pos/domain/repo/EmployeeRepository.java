package org.lamedh.pos.domain.repo;

import org.lamedh.pos.common.domain.Repository;
import org.lamedh.pos.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee> {
    Optional<Employee> getByCode(String code);
}
