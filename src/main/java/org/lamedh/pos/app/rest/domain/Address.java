package org.lamedh.pos.app.rest.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String zip;
    private String country;

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

    Address() {}
}
