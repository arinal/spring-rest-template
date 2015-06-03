package org.lamedh.pos.domain.employee;

import org.lamedh.common.domain.DomainServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService extends DomainServiceBase<Employee> {

    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        super(Employee.class, employeeRepo);
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee create() {
        Employee employee = new Employee();
        employee.setCode(generateCode());
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.isTransient())
            employee.setCode(generateCode());
        return employeeRepo.save(employee);
    }

    private String generateCode() {
        return "E" + (employeeRepo.count() + 1);
    }

    public Optional<Employee> getByCode(String code) {
        return employeeRepo.getByCode(code);
    }
}
