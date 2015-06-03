package org.lamedh.common.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class DomainServiceBase<TEntity extends EntityBase> implements  DomainService<TEntity> {

    private Class<TEntity> entityClass;
    private Repository<TEntity> repository;

    public DomainServiceBase(Class<TEntity> entityClass, Repository<TEntity> repository) {
        this.entityClass = entityClass;
        this.repository = repository;
    }

    @Override
    public TEntity create() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<TEntity> getById(int id) {
        return repository.getById(id);
    }

    @Override
    public Page<TEntity> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public TEntity save(TEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
