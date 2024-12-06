# PigeonSkyRace - Sécurisation de l'Application

## Contexte du projet

Ce projet a pour objectif de sécuriser l'application **PigeonSkyRace** en permettant aux utilisateurs de s'authentifier via **Basic Authentication**. Les informations des utilisateurs, ainsi que leurs rôles, sont stockées dans une base de données. L'authentification et la gestion des utilisateurs sont gérées de manière sécurisée et flexible.

## Exigences du projet

### Sécurisation des Endpoints
- Tous les **endpoints** sont sécurisés, sauf celui pour la création d'un nouvel utilisateur.
- Les utilisateurs et leurs rôles (**ROLE_USER**, **ROLE_ADMIN**, **ROLE_ORGANIZER**) sont stockés dans une base de données.
- Les rôles doivent être enregistrés au format **ROLE_** pour être compatibles avec les exigences de **Spring Security**.
- Lors de la création d'un utilisateur, il dispose par défaut du rôle **ROLE_USER**, qui peut ensuite être modifié par un **admin**.
- Un **UserDetailsService** personnalisé gère le chargement des informations utilisateur.
- Un service spécifique permet de gérer les utilisateurs de l'application.

### Sécurité
- Un **CustomAuthenticationProvider** gère l'authentification en appelant le **UserDetailsService** personnalisé.
- Les mots de passe sont encodés avec un **PasswordEncoder** (par exemple, **BCryptPasswordEncoder**).
- **Spring Security** est configuré pour éviter la création de sessions (par défaut **JSESSIONID**) en activant l'option **stateless**.
- Le **profil Spring TEST** permet l'authentification avec n'importe quel mot de passe.

### Gestion des Exceptions
- La méthode **handle** des classes d'exception **AccessDeniedHandler** est redéfinie pour bien gérer le statut **403 Forbidden** et renvoyer des réponses expressives aux clients.
- Des exceptions personnalisées sont créées, comme **UsernameAlreadyExistsException**, pour gérer les cas où un utilisateur existe déjà lors de la création d'un nouvel utilisateur.
- Utilisation de **@RestControllerAdvice** pour gérer les exceptions de manière centralisée.
- Mise en place d'un **DTO d'erreurs** pour une réponse structurée et compréhensible.

## Technologies et Outils Utilisés

- **Spring Security** : Sécurisation de l'application avec des mécanismes d'authentification et d'autorisation.
- **Basic Authentication** : Méthode d'authentification pour valider l'identité des utilisateurs.
- **Authentication Manager** : Gestion de l'authentification au niveau de l'application.
- **Authentication Providers** : Personnalisation de la logique d'authentification.
- **UserDetailsService** : Chargement des informations utilisateur pour l'authentification.
- **Password Encoders** : Encodage des mots de passe pour assurer leur sécurité (ex : **BCryptPasswordEncoder**).
- **SecurityContext** : Gestion du contexte de sécurité pour les utilisateurs authentifiés.
- **Encodage, Chiffrement, Hashage** : Garantir la sécurité des données sensibles, notamment des mots de passe.

## Architecture

Le projet suit une architecture basée sur **Spring Boot** et **Spring Security**, avec une approche de sécurisation **stateless**. Les rôles et utilisateurs sont gérés de manière centralisée avec un service dédié à la gestion des utilisateurs. L'application garantit une gestion fluide des exceptions et des réponses expressives en cas d'erreurs.

## Fonctionnalités
- **Création d'un utilisateur** : Permet l'enregistrement d'un nouvel utilisateur avec un rôle par défaut de **ROLE_USER**.
- **Modification des rôles utilisateur** : Les administrateurs peuvent modifier les rôles des utilisateurs.
- **Authentification** : Utilisation de **Basic Authentication** pour sécuriser les accès aux endpoints de l'application.
- **Gestion des erreurs** : Gestion centralisée des erreurs et des exceptions via un DTO d'erreur et des réponses adaptées.

## Installation

1. Clonez ce repository :
   ```bash
   git clone https://github.com/kholoud001/PigeonSkyRaceSpringSecurity.git
