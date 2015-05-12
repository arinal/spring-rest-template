package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.EmployeeController;
import org.lamedh.pos.common.app.rest.HateoasResource;
import org.lamedh.pos.domain.Employee;

public class EmployeeResource extends HateoasResource<Employee, EmployeeController> {
    public EmployeeResource(Employee employee) {
        super(employee, new EmployeeController(null));
    }
}

