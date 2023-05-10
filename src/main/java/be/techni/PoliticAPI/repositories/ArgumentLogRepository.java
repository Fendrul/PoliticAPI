package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.ArgumentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArgumentLogRepository extends JpaRepository<ArgumentLog, Long> {
    @Query("SELECT al FROM ArgumentLog al WHERE al.argument.argument_id = ?1")
    List<ArgumentLog> findAllByArgumentId(Long argumentId);
}