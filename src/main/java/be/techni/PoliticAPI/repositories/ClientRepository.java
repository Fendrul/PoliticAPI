package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client c where c.name like ?1")
    Optional<Client> findByName(String name);
}