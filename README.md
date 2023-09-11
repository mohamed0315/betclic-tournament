# Projet Ktor avec MongoDB

Ce projet est un exemple simple d'application Ktor utilisant MongoDB comme base de données. Il expose une API pour gérer les joueurs dans un tournoi, notamment pour créer, mettre à jour et récupérer des joueurs, trier les joueurs par score, et supprimer tous les joueurs à la fin du tournoi.

## Configuration du fichier start.sh de lancement de l'application


Assurez-vous d'avoir une instance MongoDB en cours d'exécution. Vous devrez configurer la chaîne de connexion MongoDB en utilisant une variable d'environnement avant de démarrer l'application. Exemple de chaîne de connexion :

```bash
export MONGODB_URI="mongodb+srv://votre_identifiant:votre_mot_de_passe@cluster.mongodb.net/votre_base_de_donnees?retryWrites=true&w=majority"

```

## Compiler l'application

Pour compiler l'application, exécutez le script gradle suivant dans le répertoire racine du projet :

```bash
./gradlew build
```

## Lancer l'application

Pour lancer l'application, exécutez le script bash dans le répertoire racine du projet :

```bash
./start.sh
```

L'application sera disponible à l'adresse http://localhost:8080. Vous pouvez tester les différentes API en utilisant un client HTTP, tel que curl ou Postman.

## API REST Routes disponibles 

### POST /player

Crée un nouveau joueur avec un pseudo

### PATCH /player/{pseudo}

Met à jour le score d'un joueur

### GET /player/{pseudo}

Récupère les données d'un joueur par son pseudo

### GET /players

Récupère les données de tous les joueurs

### GET /players/sorted

Récupère les données de tous les joueurs triés par en fonction de leur score

### DELETE /players

Supprime tous les joueurs du tournoi
