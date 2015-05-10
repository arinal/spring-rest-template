package org.lamedh.pos.domain;

import org.lamedh.pos.common.EntityBase;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Employee extends EntityBase {
    private String code;
    private String name;
    private LocalDate birthDate;

    @Embedded
    private Address address;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee() {
        birthDate = LocalDate.now().minusYears(25);
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}

