# OctoGuacamole App

## Description
OctoGuacamole est une application mobile interactive qui permet aux utilisateurs d'explorer une liste de plantes, de tester leurs connaissances à travers un quiz amusant, et de recevoir des résultats instantanés. L'application utilise l'API OpenPlants pour obtenir des informations sur différentes plantes.

## Choix de l'API
Nous avons choisi l'API [Trefle](https://trefle.io/) en raison de sa richesse en données sur les plantes. L'accès à l'API se fait via une clé API, garantissant ainsi la sécurité des données.


## Architecture et Design Patterns
L'application suit une architecture MVVM (Modèle-Vue-VueModèle) pour une séparation claire des responsabilités. Nous avons également intégré certains design patterns pour améliorer la maintenabilité et l'évolutivité du code. 
### Modèle-Vue-VueModèle (MVVM) 
L'architecture MVVM est utilisée pour organiser le code de manière à séparer la logique métier de l'interface utilisateur.
Voici une brève description de chaque composant : 
- **Modèle (Model)** : Gère les données et la logique métier. Dans notre cas, les interactions avec l'API et la gestion des données des plantes. 
- **Vue (View)** : Responsable de l'interface utilisateur. Chaque interface utilisateur (liste des plantes, quiz, résultats) est une vue distincte. 
- **VueModèle (ViewModel)** : Agit comme une couche intermédiaire entre le Modèle et la Vue. Il expose les données nécessaires à l'interface utilisateur et gère les interactions de l'utilisateur.

### Design Patterns
Nous avons incorporé les design patterns suivants pour améliorer la structure du code :
- **Singleton** : Utilisé pour garantir une seule instance de certaines classes, notamment pour la gestion de l'accès à l'API.
- **Observer** : Utilisé pour mettre à jour automatiquement l'interface utilisateur lorsque le nombre d'essai et le score changent.
- **Repository** : Utilisé pour centraliser la logique d'accès aux données, facilitant la gestion des sources de données multiples (locale et distante).
## Fonctionnalités

### 1. Liste des Plantes
- Affichage d'une liste de plantes avec des détails tels que le nom, l'image.
![[page1.png]]

### 2. Quiz sur les Plantes
- Affichage d'une image de plante avec trois choix de noms possibles.
- Validation en temps réel des réponses avec un retour visuel.
- Suivi du score pendant le quiz.
![[page2.png]]

### 3. Résultats du Quiz
- Affichage du score et des réponses correctes et incorrectes.
- Possibilité de reinitialiser le score.
![[page3.png]]

## Gestion des Erreurs de Connexion
- Détection automatique de l'absence de connexion Internet.
- Messages d'erreur clairs et informatifs pour informer l'utilisateur.
- Automatisation du rafraîchissement de la page lorsqu'une reconnexion est détectée.
![[page4.png]]

## Comment exécuter l'application
1. Clonez le dépôt : 
```
git clone https://github.com/louaybadri/riddle-octo-guacamole.git 
```
2. Ouvrez le projet dans Android Studio.
3. Compilez et exécutez l'application sur un émulateur ou un appareil Android.


## Dépendances
- Kotlin 1.5.+
- Android Gradle Plugin 7.0.+
- Retrofit 2.9.+ pour les appels API.
- Glide 14.16.+ pour le chargement des images.



