package ma.formations.ioc.IOC.service;

import ma.formations.ioc.IOC.dao.IDao;
import ma.formations.ioc.IOC.service.model.Article;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements IService {
    private final IDao dao;

    public ServiceImpl(@Qualifier("dao1") IDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Article> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Article article) {
        dao.save(article);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Article findById(Long id) {
        return dao.findById(id);
    }
}
