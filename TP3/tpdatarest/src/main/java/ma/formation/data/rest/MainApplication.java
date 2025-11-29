package ma.formation.data.rest;

import lombok.AllArgsConstructor;
import ma.formation.data.rest.dao.ArticleRepository;
import ma.formation.data.rest.dao.CategorieRepository;
import ma.formation.data.rest.service.model.Article;
import ma.formation.data.rest.service.model.Categorie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(CategorieRepository categorieRepository, ArticleRepository articleRepository) {
        return (a) -> {
            var categorie1 = Categorie.builder().categorie("CATEGORIE_1").build();
            var categorie2 = Categorie.builder().categorie("CATEGORIE_2").build();
            var categorie3 = Categorie.builder().categorie("CATEGORIE_3").build();

            var article1 = Article.builder().description("Article_1").price(5000.0).quantity(10.0).build();
            var article2 = Article.builder().description("Article_2").price(6000.0).quantity(20.0).build();
            var article3 = Article.builder().description("Article_3").price(7000.0).quantity(30.0).build();
            var article4 = Article.builder().description("Article_4").price(8000.0).quantity(40.0).build();
            var article5 = Article.builder().description("Article_5").price(9000.0).quantity(50.0).build();



            categorie1 = categorieRepository.save(categorie1);
            categorie2 = categorieRepository.save(categorie2);
            categorie3 = categorieRepository.save(categorie3);

            article1=articleRepository.save(article1);
            article2=articleRepository.save(article2);
            article3=articleRepository.save(article3);
            article4=articleRepository.save(article4);
            article5=articleRepository.save(article5);

            categorie1.setArticles(new ArrayList<>());
            categorie1.addArticle(article1);
            categorie1.addArticle(article2);

            categorie2.setArticles(new ArrayList<>());
            categorie2.addArticle(article3);
            categorie2.addArticle(article4);

            categorie3.setArticles(new ArrayList<>());
            categorie3.addArticle(article5);

            articleRepository.save(article1);
            articleRepository.save(article2);
            articleRepository.save(article3);
            articleRepository.save(article4);
            articleRepository.save(article5);

        };
    }
}
