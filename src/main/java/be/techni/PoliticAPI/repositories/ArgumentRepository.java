package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.Argument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArgumentRepository extends JpaRepository<Argument, Long> {
    @Query(nativeQuery = true, value = """
            select * from argument
            where argument.state = 'VALIDATED'
            order by argument.date DESC
            limit :listLength
                    """)
    List<Argument> findLastValidatedArguments(long listLength);

    @Query("""
            select a from Argument a
            JOIN Category c
            where c.category_id = :category_id
            order by a.date DESC
                    """)
    List<Argument> findAllByCategoryId(Long category_id);

    @Query("""
            select a from Argument a
            where a.state = 'PENDING'
            order by a.date DESC
            """)
    List<Argument> findPendingArguments(long listLength);
}
