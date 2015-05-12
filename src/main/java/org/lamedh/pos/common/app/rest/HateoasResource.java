package org.lamedh.pos.common.app.rest;

import org.lamedh.pos.common.domain.EntityBase;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public abstract class HateoasResource<TEntity extends EntityBase, TController extends RestControllerBase>
        extends ResourceSupport {
    private final TEntity entity;

    public TEntity getContent() {
        return entity;
    }

    public HateoasResource(TEntity entity, TController controller) {
        this.entity = entity;
        this.add(ControllerLinkBuilder.linkTo(controller.getClass()).withRel("employees"));
        this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(controller.getClass())
                .get(entity.getId())).withSelfRel());
    }
}
