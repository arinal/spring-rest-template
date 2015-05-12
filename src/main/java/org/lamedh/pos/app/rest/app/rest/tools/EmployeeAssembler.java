package org.lamedh.pos.app.rest.app.rest.tools;

import org.lamedh.pos.app.rest.app.rest.EmployeeController;
import org.lamedh.pos.app.rest.domain.Employee;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class EmployeeAssembler extends ResourceAssemblerSupport<Employee, Resource> {

    public EmployeeAssembler() {
        super(EmployeeController.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends Employee> employees) {
        List<Resource> resources = new ArrayList<>();
        for(Employee e : employees)
            resources.add(new Resource<>(e, linkTo(EmployeeController.class).slash(e.getId()).withSelfRel()));
        return resources;
    }

    @Override
    public Resource toResource(Employee employee) {
        return new Resource<>(employee, linkTo(EmployeeController.class).slash(employee.getId()).withSelfRel());
    }
}