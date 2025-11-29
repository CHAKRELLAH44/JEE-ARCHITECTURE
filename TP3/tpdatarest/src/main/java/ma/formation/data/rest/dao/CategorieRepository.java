package ma.formation.data.rest.dao;

import ma.formation.data.rest.service.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
