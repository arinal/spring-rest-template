package org.lamedh.pos.app.rest;

import org.lamedh.pos.app.rest.tools.NotFoundException;
import org.lamedh.pos.domain.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.lamedh.pos.domain.Employee;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;

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
        return id == 0 ? service.create() : service.getById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @RequestMapping
    Iterable<Employee> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> save(@RequestBody Employee employee) {
        Employee savedEmployee = service.save(employee);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
}
