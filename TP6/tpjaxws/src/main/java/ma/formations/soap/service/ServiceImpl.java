package ma.formations.soap.service;

import ma.formations.soap.service.model.Article;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements IService {
    private static final List<Article> repository = new ArrayList<>();

    static {
        repository.add(Article.builder().id(1L).description("Article_1").price(12000.0).quantity(10.0).build());
        repository.add(Article.builder().id(2L).description("Article_2").price(13000.0).quantity(20.0).build());
        repository.add(Article.builder().id(3L).description("Article_3").price(13000.0).quantity(30.0).build());
        repository.add(Article.builder().id(4L).description("Article_4").price(14000.0).quantity(40.0).build());
        repository.add(Article.builder().id(5L).description("Article_5").price(15000.0).quantity(50.0).build());
    }

    @Override
    public List<Article> getAll() {
        return repository;
    }

    @Override
    public Article save(Article article) {
        try {
            getById(article.getId());
            deleteById(article.getId());
            repository.add(article);
            return article;
        } catch (Exception ex) {
            repository.add(article);
            return article;
        }
    }

    @Override
    public void deleteById(Long id) {
        Article articleFound = getById(id);
        repository.remove(articleFound);
    }

    @Override
    public Article getById(Long id) {
        return repository.stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No article with id=%s", id)));
    }
}