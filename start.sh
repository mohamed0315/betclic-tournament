#!/bin/bash

# Définition de la variable d'environnement MONGODB_URI
export MONGODB_URI="mongodb://localhost:27017/betclic-tournament"

# Nom du fichier JAR généré
JAR_FILE="build/libs/betclic-tournament-all.jar"

# Vérifie si le fichier JAR existe
if [ ! -f "$JAR_FILE" ]; then
  echo "Le fichier JAR '$JAR_FILE' n'a pas été trouvé. Assurez-vous qu'il est dans le répertoire actuel."
  exit 1
fi

# Exécution du JAR
java -DMONGODB_URI="$MONGODB_URI" -jar "$JAR_FILE"