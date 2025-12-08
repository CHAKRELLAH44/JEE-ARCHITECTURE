# TP – API GraphQL avec Spring Boot : Gestion des Produits & Catégories

Ce projet illustre la mise en place d'une API GraphQL permettant de gérer des **catégories** et des **produits** à l'aide de Spring Boot 3, Spring Data JPA et GraphQL.  
Il contient toutes les fonctionnalités CRUD complètes et tous les tests à exécuter via l'interface GraphiQL.

## 📌 1. Objectif du TP

- Comprendre la structure d'un projet GraphQL sous Spring Boot
- Implémenter des **queries** et **mutations**
- Gérer des entités relationnelles : *Produit* ↔ *Catégorie*
- Tester l'API via GraphiQL
- Produire un workflow clair pour un rapport académique

## 🏗️ 2. Architecture du projet

### 📁 Structure principale

```
src/main/java
└── com.example.demo
    ├── model
    │   ├── Produit.java
    │   └── Categorie.java
    ├── repository
    │   ├── ProduitRepository.java
    │   └── CategorieRepository.java
    ├── service
    │   ├── ProduitService.java
    │   └── CategorieService.java
    ├── graphql
    │   ├── ProduitController.java
    │   └── CategorieController.java
    └── DemoApplication.java

src/main/resources
├── application.properties
└── graphql/schema.graphqls
```

## ⚙️ 3. Technologies utilisées

| Technologie | Description |
|-------------|-------------|
| **Spring Boot 3** | Framework principal |
| **Spring Web** | Serveur HTTP intégré |
| **Spring Data JPA** | Gestion ORM / Base de données |
| **H2 Database** | Base embarquée pour les tests |
| **Spring GraphQL** | Exposition GraphQL |
| **GraphiQL** | Interface Web de test |
| **Lombok** | Génération automatique du boilerplate code |

## 🚀 4. Lancement du projet

1. Ouvrir le projet dans IntelliJ / VS Code
2. Vérifier la configuration du fichier `application.properties` :

```properties
server.port=9193
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.graphql.graphiql.enabled=true
```

3. Exécuter la classe :
   `DemoApplication.java`

4. Ouvrir GraphiQL dans le navigateur :
    [http://localhost:9193/graphiql?path=/graphql](http://localhost:9193/graphiql?path=/graphql)

## 🧪 5. Tests GraphQL à exécuter
### ✔ Schéma GraphQL (schema.graphqls)
- types
- mutations
- queries
- inputs
###  5.1. Ajouter une catégorie

```graphql
mutation {
  ajouterCategorie(input: { nom: "Informatique" }) {
    id
    nom
  }
}
```

###  5.2. Ajouter un produit

```graphql
mutation {
  ajouterProduit(input: { nom: "PC Dell", prix: 9500, categorieId: 1 }) {
    id
    nom
    prix
    categorie { nom }
  }
}
```

###  5.3. Lister les produits

```graphql
query {
  produits {
    id
    nom
    prix
    categorie {
      nom
    }
  }
}
```

###  5.4. Modifier un produit

```graphql
mutation {
  modifierProduit(
    id: 1,
    input: { nom: "PC HP", prix: 8900, categorieId: 1 }
  ) {
    id
    nom
    prix
  }
}
```

###  5.5. Supprimer un produit

```graphql
mutation {
  supprimerProduit(id: 1)
}
```

##  6. Workflow général expliqué

###  Étape 1 — Le client envoie une Query ou Mutation GraphQL
La requête arrive dans l'un des contrôleurs GraphQL via :
`@QueryMapping` ou `@MutationMapping`

### ️ Étape 2 — Le contrôleur appelle le Service
Les services appliquent la logique métier (ex : vérifier l'existence d'une catégorie)

###  Étape 3 — Le Service appelle le Repository
Les repositories interagissent avec la base H2 via Spring Data JPA.

###  Étape 4 — Spring Boot mappe la réponse au format GraphQL
Grâce aux DTO et au schéma `schema.graphqls`

###  Étape 5 — GraphiQL affiche le résultat
Le client ne reçoit que les champs demandés → avantage de GraphQL.







## 🎯 7. Conclusion du projet

Ce TP te permet de :
- maîtriser la syntaxe GraphQL
- comprendre la structure Spring Boot + GraphQL
- implémenter un CRUD complet
- tester efficacement via GraphiQL
- préparer un rapport professionnel et clair

GraphQL apporte flexibilité, performance et un contrôle précis sur les données retournées.