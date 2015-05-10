package org.lamedh.pos.app.rest;

import org.lamedh.pos.domain.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.lamedh.pos.domain.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping("/{id}")
    Employee get(@PathVariable int id) {
        return id == 0 ? service.create() : service.getById(id);
    }

    @RequestMapping
    Iterable<Employee> getAll() {
        return service.getAll();
    }
}
