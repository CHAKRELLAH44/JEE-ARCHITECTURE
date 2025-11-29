package ma.formation.data.rest.dao;

import ma.formation.data.rest.domaine.ArticleDTO;
import ma.formation.data.rest.service.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "articles", path = "ecommerce", excerptProjection = ArticleDTO.class)
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @RestResource(path = "byCategorie", rel = "customFindByDescription")
    List<Article> findByCategorie_Categorie(@Param("categorie") String description);
}
