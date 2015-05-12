package org.lamedh.pos.common.app.rest;

import org.lamedh.pos.common.domain.EntityBase;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public abstract class HateoasResource<TEntity extends EntityBase> extends ResourceSupport {

    private final TEntity entity;

    public TEntity getContent() {
        return entity;
    }

    public HateoasResource(TEntity entity, Class<? extends RestControllerBase> controllerClass) {
        this.entity = entity;
        this.add(ControllerLinkBuilder.linkTo(controllerClass).withRel("all"));
        this.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(controllerClass).get(entity.getId())).withSelfRel());
    }
}
