package org.lamedh.pos.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lamedh.pos.domain.repo.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee create() {
        Employee employee = new Employee();
        employee.setCode(generateCode());
        return employee;
    }

    public Employee save(Employee employee) {
        employee.setCode(generateCode());
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getById(int id) {
        return employeeRepo.getById(id);
    }

    public Iterable<Employee> getAll() {
        return employeeRepo.findAll();
    }

    private String generateCode() {
        return "E" + (employeeRepo.count() + 1);
    }
}
