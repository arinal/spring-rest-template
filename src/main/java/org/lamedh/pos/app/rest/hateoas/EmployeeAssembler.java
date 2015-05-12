package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.EmployeeController;
import org.lamedh.pos.common.app.rest.EntityAssembler;
import org.lamedh.pos.domain.Employee;

public class EmployeeAssembler extends EntityAssembler<Employee> {
    public EmployeeAssembler() {
        super(EmployeeController.class, EmployeeResource::new);
    }
}