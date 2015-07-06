package org.lamedh.common.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DomainService<TEntity> {
    TEntity create();
    Optional<TEntity> getById(int id);
    Page<TEntity> getAll(String search, Pageable page);
    Page<TEntity> getAll(Pageable page);

    TEntity save(TEntity entity);
    void delete(int id);
}

