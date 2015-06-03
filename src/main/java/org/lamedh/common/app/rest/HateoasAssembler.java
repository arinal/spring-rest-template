package org.lamedh.common.app.rest;

import org.lamedh.common.domain.EntityBase;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;
import java.util.stream.Collectors;

import static org.lamedh.common.iterable.IterableCommon.stream;

public class HateoasAssembler<TEntity extends EntityBase> extends ResourceAssemblerSupport<TEntity, Resource> {

    Class<?> controllerClass;

    public HateoasAssembler(Class<?> controllerClass) {
        super(controllerClass, Resource.class);
        this.controllerClass = controllerClass;
    }

    public List<Resource> toResources(Iterable<? extends TEntity> entities) {
        return stream(entities)
                .map(e -> new Resource(e, createResourceWithId(e.getId(), e).getLink("self")))
                .collect(Collectors.toList());
    }

    @Override
    public Resource toResource(TEntity entity) {
        return new Resource(entity, ControllerLinkBuilder.linkTo(controllerClass)
                .slash(Integer.valueOf(entity.getId())).withSelfRel());
    }
}
