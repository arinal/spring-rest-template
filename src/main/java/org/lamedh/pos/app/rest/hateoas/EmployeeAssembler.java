package org.lamedh.pos.app.rest.hateoas;

import org.lamedh.pos.app.rest.EmployeeController;
import org.lamedh.pos.common.app.rest.HateoasAssembler;
import org.lamedh.pos.domain.Employee;

public class EmployeeAssembler extends HateoasAssembler<Employee> {
    public EmployeeAssembler() {
        super(EmployeeController.class, EmployeeResource::new);
    }
}