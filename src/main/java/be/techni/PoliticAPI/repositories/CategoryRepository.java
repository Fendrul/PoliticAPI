package be.techni.PoliticAPI.repositories;

import be.techni.PoliticAPI.models.entities.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, BaseRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Specification mySpec = (root, query, cb) -> cb.equal(root.get("name"), "test");
    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    Optional<Category> findByName(String categoryName);
}