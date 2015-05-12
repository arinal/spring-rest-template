package org.lamedh.pos.app.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class EntityBase {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!o.getClass().equals(getClass())) return false;
        EntityBase that = (EntityBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
