package org.lamedh.pos.domain.repo;

import org.lamedh.pos.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

