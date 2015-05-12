package org.lamedh.pos.app.rest;

import org.lamedh.pos.app.rest.hateoas.EmployeeAssembler;
import org.lamedh.pos.app.rest.hateoas.EmployeeResource;
import org.lamedh.pos.common.app.rest.RestControllerBase;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends RestControllerBase<Employee, EmployeeService> {

    @Autowired
    public EmployeeController(EmployeeService service) {
        super(service, new EmployeeAssembler(), EmployeeResource::new);
    }
}

