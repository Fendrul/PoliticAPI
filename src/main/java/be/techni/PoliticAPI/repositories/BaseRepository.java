package be.techni.PoliticAPI.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    T findOne(Specification<T> spec, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);

    List<T> findAll(Specification<T> spec, Sort sort, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);

}

