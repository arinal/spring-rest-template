package org.lamedh.pos.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private final String street;
    private final String zip;
    private final String state;

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public Address(String street, String zip, String state) {
        this.street = street;
        this.zip = zip;
        this.state = state;
    }
}
