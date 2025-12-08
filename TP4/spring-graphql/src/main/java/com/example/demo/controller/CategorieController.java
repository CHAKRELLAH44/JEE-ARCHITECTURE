package com.example.demo.controller;

import com.example.demo.model.Categorie;
import com.example.demo.repository.CategorieRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategorieController {
    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }
    @QueryMapping
    public List<Categorie> categories() {
        return categorieRepository.findAll();
    }

    @QueryMapping
    public Categorie categorie(@Argument Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Categorie ajouterCategorie(@Argument CategorieInput input) {
        Categorie categorie = new Categorie(input.nom());
        return categorieRepository.save(categorie);
    }

    @MutationMapping
    public Boolean supprimerCategorie(@Argument Long id) {
        categorieRepository.deleteById(id);
        return true;
    }

    public record CategorieInput(String nom) {}
}

