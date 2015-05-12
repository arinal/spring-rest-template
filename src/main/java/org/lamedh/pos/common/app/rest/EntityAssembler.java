package org.lamedh.pos.common.app.rest;

import org.lamedh.pos.common.app.rest.HateoasResource;
import org.lamedh.pos.common.domain.EntityBase;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.lamedh.pos.common.iterable.IterableCommon.stream;

public class EntityAssembler<TEntity extends EntityBase> extends ResourceAssemblerSupport<TEntity, Resource> {

    private final Function<TEntity, HateoasResource> wrapWithResource;
    private final Class<?> controllerClass;

    public EntityAssembler(Class<?> controllerClass, Function<TEntity, HateoasResource> wrapWithResource) {
        super(controllerClass, Resource.class);
        this.wrapWithResource = wrapWithResource;
        this.controllerClass = controllerClass;
    }

    public List<Resource> toResources(Iterable<? extends TEntity> entities) {
        return stream(entities)
                .map(e -> new Resource(e, wrapWithResource.apply(e).getLink("self")))
                .collect(Collectors.toList());
    }

    @Override
    public Resource toResource(TEntity entity) {
        return new Resource(entity, ControllerLinkBuilder.linkTo(controllerClass)
                .slash(Integer.valueOf(entity.getId())).withSelfRel());
    }
}
