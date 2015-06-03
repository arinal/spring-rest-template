package org.lamedh.common.app.rest;

import org.lamedh.common.domain.DomainService;
import org.lamedh.common.domain.EntityBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

public abstract class RestControllerBase<TEntity extends EntityBase> {
    private final ResourceAssemblerSupport entityAssembler;
    private final DomainService<TEntity> service;

    protected RestControllerBase(DomainService<TEntity> service) {
        this.service = service;
        this.entityAssembler = new HateoasAssembler<>(this.getClass());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResourceSupport get(@PathVariable int id) {
        TEntity entity = id == 0? service.create(): this.service.getById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return entityAssembler.toResource(entity);
    }

    @RequestMapping(method = RequestMethod.GET)
    PagedResources<TEntity> getAll(Pageable page, PagedResourcesAssembler assembler) {
        Page entities = service.getAll(page);
        return assembler.toResource(entities, entityAssembler);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity save(@RequestBody TEntity entity) {
        int id = entity.getId();
        TEntity saved = service.save(entity);
        Link link = entityAssembler.toResource(saved).getLink("self");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(link.getHref()));
        return new ResponseEntity(httpHeaders, id == 0? HttpStatus.CREATED : HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
