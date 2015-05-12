package org.lamedh.pos.app.rest.app.rest.tools;

import org.lamedh.pos.app.rest.app.rest.EmployeeController;
import org.lamedh.pos.app.rest.domain.Employee;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EmployeeResource extends ResourceSupport {
    private final Employee employee;

    public Employee getContent() {
        return employee;
    }

    public EmployeeResource (Employee employee) {
        this.employee = employee;
        this.add(linkTo(EmployeeController.class).withRel("employees"));
        this.add(ControllerLinkBuilder.linkTo(methodOn(EmployeeController.class).get(employee.getId())).withSelfRel());
    }
}
