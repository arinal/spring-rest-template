package org.lamedh.pos.common.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface Repository<T extends EntityBase> extends JpaRepository<T, Integer> {
    Optional<T> getById(int id);
}
