package service

import repository.PlayerRepository

class RankingService(private val playerRepository: PlayerRepository) {
    fun calculateTournamentRank() {
         //Logique de calcul du classement
        val allPlayers = playerRepository.getAllPlayers()

        val sortedPlayers = allPlayers?.sortedByDescending { it.score }

        sortedPlayers?.forEachIndexed { index, player ->
            player.rank = index + 1
            // Mettre à jour le classement dans la source de données
            playerRepository.updatePlayerScore(player.pseudo, player.score)
        }
    }

    fun getPlayerRank(pseudo: String): Int? {
        val allPlayers = playerRepository.getAllPlayers()
        val sortedPlayers = allPlayers?.sortedByDescending { it.score }

        if (sortedPlayers != null) {
            for ((index, player) in sortedPlayers.withIndex()) {
                if (player.pseudo == pseudo) {
                    return index + 1 // Classement basé sur l'index dans la liste triée
                }
            }
        }
        return null // Le joueur n'a pas été trouvé
    }
}
