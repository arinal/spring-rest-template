package org.lamedh.pos.common.app.rest;

import org.lamedh.pos.app.rest.tools.NotFoundException;
import org.lamedh.pos.common.domain.DomainService;
import org.lamedh.pos.common.domain.EntityBase;
import org.lamedh.pos.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
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

public abstract class RestControllerBase<TEntity extends EntityBase, TService extends DomainService<TEntity>, TResource extends HateoasResource> {
    private final ResourceAssemblerSupport entityAssembler;
    private final TService service;

    protected RestControllerBase(TService service, ResourceAssemblerSupport entityAssembler) {
        this.service = service;
        this.entityAssembler = entityAssembler;
    }

    @RequestMapping({"/{id}"})
    public TResource get(@PathVariable int id) {
        TEntity entity = id == 0? service.create(): this.service.getById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return createResource(entity);
    }

    @RequestMapping
    PagedResources<Product> getAll(Pageable page, PagedResourcesAssembler assembler) {
        Page entities = service.getAll(page);
        return assembler.toResource(entities, entityAssembler);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> save(@RequestBody TEntity entity) {
        int id = entity.getId();
        TEntity saved = service.save(entity);
        Link link = createResource(saved).getLink("self");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(link.getHref()));
        return new ResponseEntity(httpHeaders, id == 0? HttpStatus.CREATED : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable int id) {
        this.service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    protected abstract TResource createResource(TEntity entity);
}
