package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.ArgumentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArgumentLogRepository extends JpaRepository<ArgumentLog, Long> {
}