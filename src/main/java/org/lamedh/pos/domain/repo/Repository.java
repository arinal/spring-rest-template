package org.lamedh.pos.domain.repo;

import org.lamedh.pos.common.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface Repository<T extends EntityBase> extends JpaRepository<T, Integer> {
    Optional<T> getById(int id);
}
