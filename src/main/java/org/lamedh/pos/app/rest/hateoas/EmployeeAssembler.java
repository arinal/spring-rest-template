package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.EmployeeController;
import org.lamedh.pos.domain.Employee;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAssembler extends ResourceAssemblerSupport<Employee, Resource> {
    public EmployeeAssembler() {
        super(EmployeeController.class, Resource.class);
    }

    public List<Resource> toResources(Iterable<? extends Employee> employees) {
        List<Resource> resources = new ArrayList<>();
        for (Employee e:employees)
            resources.add(new Resource(e, new EmployeeResource(e).getLink("self")));
        return resources;
    }

    public Resource toResource(Employee employee) {
        return new Resource(employee, ControllerLinkBuilder.linkTo(EmployeeController.class).slash(Integer.valueOf(employee.getId())).withSelfRel());
    }
}
