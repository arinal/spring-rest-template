package org.lamedh.pos.app.rest;

import org.lamedh.common.app.rest.RestControllerBase;
import org.lamedh.pos.domain.employee.Employee;
import org.lamedh.pos.domain.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/employee")
public class EmployeeController extends RestControllerBase<Employee> {
    @Autowired
    public EmployeeController(EmployeeService service) {
        super(service);
    }
}

