# Social Media Application

Une application de médias sociaux similaire à Twitter développée avec Spring Boot.

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

1. Clonez ce dépôt
2. Naviguez vers le dossier du projet
3. Exécutez `mvn spring-boot:run`
4. Accédez à l'application dans votre navigateur à l'adresse `http://localhost:8080`

## Structure du projet

- `src/main/java/com/socialmedia/app/model` - Entités JPA
- `src/main/java/com/socialmedia/app/repository` - Repositories Spring Data
- `src/main/java/com/socialmedia/app/service` - Services métier
- `src/main/java/com/socialmedia/app/controller` - Contrôleurs MVC
- `src/main/java/com/socialmedia/app/config` - Configuration Spring
- `src/main/resources/templates` - Templates Thymeleaf
- `src/main/resources/static` - Ressources statiques (CSS, JS, images)
