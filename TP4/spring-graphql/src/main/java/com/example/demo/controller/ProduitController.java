package com.example.demo.controller;
import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProduitController {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProduitController(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    @QueryMapping
    public List<Produit> produits() {
        return produitRepository.findAll();
    }

    @QueryMapping
    public Produit produit(@Argument Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Produit ajouterProduit(@Argument("input") ProduitInput input) {
        Categorie categorie = categorieRepository.findById(input.categorieId()).orElse(null);
        Produit produit = new Produit(input.nom(), input.prix(), categorie);
        return produitRepository.save(produit);
    }

    @MutationMapping
    public Produit modifierProduit(@Argument Long id, @Argument ProduitInput input) {
        Produit produit = produitRepository.findById(id).orElseThrow();
        produit.setNom(input.nom());
        produit.setPrix(input.prix());
        Categorie categorie = categorieRepository.findById(input.categorieId()).orElse(null);
        produit.setCategorie(categorie);
        return produitRepository.save(produit);
    }

    @MutationMapping
    public Boolean supprimerProduit(@Argument Long id) {
        produitRepository.deleteById(id);
        return true;
    }

    public record ProduitInput(String nom, double prix, Long categorieId) {}
}
