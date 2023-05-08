package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username like ?1")
    Optional<User> findByName(String name);

    @Query("select u from User u where u.username like ?1")
    Optional<User> findByUsername(String username);

    @Query("""
            select case when count(u) > 0 then true else false end
            from User u
            where u.username like ?1
""")
    Boolean existsByName(String username);
}