package org.lamedh.pos.app.rest.app.rest;

import org.lamedh.pos.app.rest.app.rest.tools.EmployeeAssembler;
import org.lamedh.pos.app.rest.app.rest.tools.EmployeeResource;
import org.lamedh.pos.app.rest.app.rest.tools.NotFoundException;
import org.lamedh.pos.app.rest.domain.Employee;
import org.lamedh.pos.app.rest.domain.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping("/{id}")
    public EmployeeResource get(@PathVariable int id) {
        Employee employee = id == 0 ? service.create() : service.getById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return new EmployeeResource(employee);
    }

    @RequestMapping
    PagedResources<Employee> getAll(Pageable page, PagedResourcesAssembler assembler) {
        Page<Employee> employees = service.getAll(page);
        return assembler.toResource(employees, new EmployeeAssembler());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> save(@RequestBody Employee employee) {
        Employee savedEmployee = service.save(employee);
        Link link = new EmployeeResource(savedEmployee).getLink("self");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(link.getHref()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}