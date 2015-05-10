package org.lamedh.pos.app.rest;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        this(new Integer(id).toString());
    }

    public NotFoundException(String id) {
        super("could not find entity with id '" + id + "'");
    }
}
