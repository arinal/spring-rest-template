package org.lamedh.pos.domain.employee;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private final String street;
    private final String zip;
    private final String country;

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public Address(String street, String zip, String country) {
        this.street = street;
        this.zip = zip;
        this.country = country;
    }

    Address() { this(null, null, null); }
}
