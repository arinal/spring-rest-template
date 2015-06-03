package org.lamedh.common.domain;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@NoRepositoryBean
public interface Repository<T extends EntityBase> extends PagingAndSortingRepository<T, Integer> {
    Optional<T> getById(int id);
}
