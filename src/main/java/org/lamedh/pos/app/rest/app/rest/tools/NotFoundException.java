package org.lamedh.pos.app.rest.app.rest.tools;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        this(Integer.valueOf(id).toString());
    }

    public NotFoundException(String id) {
        super("could not find entity with id '" + id + "'");
    }
}