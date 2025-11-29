package ma.formation.data.rest.domaine;

import ma.formation.data.rest.service.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "articleDTO", types = Article.class)
public interface ArticleDTO {
    Long getId();

    @Value("#{target.description}")
    String getDesc();

    Double getPrice();

    @Value("#{target.quantity}")
    Double getQuant();

    @Value("#{target.categorie.categorie}")
    String getCat();

}
