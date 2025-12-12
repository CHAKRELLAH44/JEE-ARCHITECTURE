
## 📌 **1. Objectif du TP**

Ce TP a pour objectif de montrer comment un même service métier (gestion des clients, comptes et transactions) peut être exposé à travers **plusieurs types d’API** :

* **REST**
* **GraphQL**
* **SOAP**
* **gRPC**

Chaque technologie possède ses avantages et s’adapte à différents besoins :
REST pour les applications Web/Mobile, GraphQL pour des données optimisées, SOAP pour les environnements critiques, et gRPC pour les microservices haute performance.

---

##  **2. Architecture générale**

Le projet suit une architecture en couches :

* **Entities** : modèles (Customer, BankAccount, Transaction)
* **Repositories** : accès BD
* **Services** : logique métier
* **Controllers** : exposition des API (REST / GraphQL / SOAP / gRPC)
* **DTO** : objets d’échange de données
* **schema.graphqls** : schéma GraphQL
* **bank.proto** : contrat gRPC

---

##  **3. Tests par technologie (liens + outils)**

###  **A. REST API**

 **Outil : Swagger UI**
 **Lien :**
 [http://localhost:8080/api/rest/docs-ui](http://localhost:8080/api/rest/docs-ui)


---

###  **B. GraphQL API**

 **Outil : GraphiQL**
 **Lien :**
 [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql)


---

###  **C. SOAP API**

 **Outil : SoapUI**
 **Lien WSDL :**
 [http://localhost:8080/api/soap/BankService?wsdl](http://localhost:8080/api/soap/BankService?wsdl)


---

###  **D. gRPC API**

 **Outil : BloomRPC**
 **Serveur gRPC :**
 `0.0.0.0:4444`
 **Proto file à importer :**
`src/main/resources/bank.proto`



---

##  **4. Outils nécessaires**

* **Swagger UI** → tester REST
* **GraphiQL** → tester GraphQL
* **SoapUI** → tester SOAP
* **BloomRPC** → tester gRPC
* **IntelliJ / Spring Boot** → exécuter le backend
* **H2 Console / MySQL** → base de données

---

## **5. Conclusion**

Ce TP démontre qu’un même service métier peut être exposé via différentes technologies selon les besoins :

* **REST** → simple et largement utilisé
* **GraphQL** → flexible et optimisé
* **SOAP** → fiable et sécurisé pour les environnements critiques
* **gRPC** → très performant pour les microservices

Cette multi-exposition permet une compatibilité maximale avec différents types d'applications (anciennes, modernes, mobiles, internes, externes).


