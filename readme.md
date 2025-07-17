# Delivery Kata Application

## ✨ Description

Cette application est un exemple de **kata de gestion de livraison**, développée en **Java 21** avec **Spring Boot 3.5.3** en suivant une **architecture Hexagonale (Ports & Adapters)** et les principes du **Domain-Driven Design (DDD)**. L'API REST est non bloquante grâce à **Spring WebFlux**.

Le **stockage des données** est fait **en mémoire**, pour simplifier la démonstration.

---

## 🔧 Fonctionnalités principales

* Consultation d’une commande par son identifiant.
* Choix du mode de livraison d’une commande existante.
* Validation des paramètres REST avec **Jakarta Validation**.
* Gestion propre des erreurs via **exceptions métier**.
* Documentation automatique de l’API avec **Swagger**. => http://localhost:8080/swagger-ui.html

---

## 📏 Architecture

### Domain

* Modèle métier pur, entités, exceptions, interfaces de use cases.
* **Aucune dépendance Spring** dans ce module.

### Application

* Services qui implémentent les use cases.

### Infrastructure

* Persistance **en mémoire**.
* Adaptateur Web **REST réactif** avec **Spring WebFlux**.

### Tests

* **JUnit 5** + **Mockito** (mocking manuel via `@TestConfiguration`).

---

## ⚙️ Technologies utilisées

* Java 21
* Spring Boot 3.5.3
* Spring WebFlux (API réactive non bloquante)
* Jakarta Bean Validation (`@NotBlank`, `@Pattern`)
* Project Reactor (Mono, Flux)
* JUnit 5 + Mockito
* OpenAPI 3 (Swagger UI)

---

## 🚀 Endpoints REST

| Méthode | URL                               | Description                                    | Paramètres                                                                                                           |
| ------- | --------------------------------- | ---------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| `GET`   | `/orders/{orderId}`               | Récupère une commande par son ID               | `orderId` (path variable, non vide)                                                                                  |
| `POST`  | `/orders/{orderId}/delivery-mode` | Choisit un mode de livraison pour une commande | `orderId` (path variable, non vide), `deliveryMode` (query param : DRIVE, DELIVERY, DELIVERY\_TODAY, DELIVERY\_ASAP) |

---

## 📃 Exemple d’appels

### Récupérer une commande

```bash
curl -X GET http://localhost:8080/orders/1
```

### Choisir un mode de livraison

```bash
curl -X POST "http://localhost:8080/orders/1/delivery-mode?deliveryMode=DRIVE"
```

---

## 🚧 Lancer l’application

1. Cloner le dépôt.
2. Compiler et lancer avec Maven :

```bash
./mvnw clean spring-boot:run
```

* L’API est accessible par défaut sur :
  `http://localhost:8080`

* La documentation Swagger est disponible sur :
  `http://localhost:8080/swagger-ui.html`

---

👤 Auteur

@BiliAhmed
