# Social Media Application

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-11-orange" alt="Java"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-blue" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Bootstrap-5.0-purple" alt="Bootstrap"/>
</p>

Une application de médias sociaux complète similaire à Twitter, développée avec Spring Boot. Cette application permet aux utilisateurs de créer des comptes, publier des tweets, suivre d'autres utilisateurs, interagir avec du contenu et bien plus encore.

## Technologies utilisées

- Spring Boot 2.7.0
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- Bootstrap 5
- H2 Database (en mémoire)

## Fonctionnalités

- Inscription et connexion des utilisateurs
- Création, modification et suppression de posts
- Profils utilisateurs personnalisables
- Système de suivi d'utilisateurs (follow/unfollow)
- Flux d'actualités personnalisé
- Page d'exploration pour découvrir de nouveaux contenus

## Comment lancer l'application

```bash
# 1. Clonez ce dépôt
git clone <URL_DU_DEPOT>

# 2. Naviguez vers le dossier du projet
cd social-media-app

# 3. Compilez le projet
mvn clean compile

# 4. Lancez l'application
mvn spring-boot:run
```

5. Accédez à l'application dans votre navigateur à l'adresse `http://localhost:8080`

## Comptes de démonstration

Après avoir lancé l'application pour la première fois, vous pouvez créer un nouveau compte en utilisant la page d'inscription.

## Structure du projet

```
social-media-app/
├── src/
│   ├── main/
│   │   ├── java/com/socialmedia/app/
│   │   │   ├── config/           # Configuration Spring et Spring Security
│   │   │   ├── controller/       # Contrôleurs MVC
│   │   │   ├── model/            # Entités JPA (User, Post, Comment, etc.)
│   │   │   ├── repository/       # Repositories Spring Data JPA
│   │   │   ├── service/          # Services métier
│   │   │   └── SocialMediaApplication.java  # Point d'entrée de l'application
│   │   └── resources/
│   │       ├── static/           # Ressources statiques (CSS, JS, images)
│   │       ├── templates/         # Templates Thymeleaf
│   │       └── application.properties  # Configuration de l'application
│   └── test/                     # Tests unitaires et d'intégration
└── pom.xml                       # Configuration Maven et dépendances
```

## Prérequis

- Java 11 ou supérieur
- Maven 3.6+
- MySQL 8.0+ (ou XAMPP avec MySQL)
- Un navigateur web moderne (Chrome, Firefox, Edge, etc.)

## Contribution

Les contributions sont les bienvenues ! N'hésitez pas à ouvrir une issue ou à soumettre une pull request.

## Licence

Ce projet est sous licence MIT.
