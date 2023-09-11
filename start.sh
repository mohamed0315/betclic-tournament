#!/bin/bash

# Nom du fichier JAR généré
export MONGODB_URI="mongodb+srv://mabouyaaqoub:mabouyaaqoub@cluster99.cpis9kb.mongodb.net/?retryWrites=true&w=majority"

JAR_FILE="build/libs/betclic-tournament-all.jar"  # Remplacez par le nom de votre fichier JAR

# Vérifie si le fichier JAR existe
if [ ! -f "$JAR_FILE" ]; then
  echo "Le fichier JAR '$JAR_FILE' n'a pas été trouvé. Assurez-vous qu'il est dans le répertoire actuel."
  exit 1
fi

# Exécute le JAR
java -DMONGODB_URI="$MONGODB_URI" -jar "$JAR_FILE"