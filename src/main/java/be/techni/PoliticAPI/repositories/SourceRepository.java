package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SourceRepository extends JpaRepository<Source, Long> {
    @Query("SELECT s FROM Source s WHERE s.description = :source")
    Optional<Source> findByDescription(String source);
}