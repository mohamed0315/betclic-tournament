package service

import dto.PlayerData
import exception.PlayerNotFoundException
import model.Player
import org.litote.kmongo.Id
import repository.PlayerRepository

class PlayerService (private val playerRepository: PlayerRepository, private val rankingService: RankingService) {
    fun createPlayer(player: Player): Id<Player>? {
        return playerRepository.createPlayer(player)
    }
    fun updatePlayerScore(pseudo: String, score: Int) {
        playerRepository.updatePlayerScore(pseudo, score)
    }
    fun getAllPlayers(): List<Player>? {
        return playerRepository.getAllPlayers()
    }
    fun getPlayerData(pseudo: String): PlayerData? {
        val player = playerRepository.getPlayerByPseudo(pseudo)
        return if (player != null) {
            val rank = rankingService.getPlayerRank(player.pseudo) // Calcul du classement
            rank?.let { PlayerData(player.pseudo, player.score, it) }

        } else {
            throw PlayerNotFoundException("Joueur avec le pseudo $pseudo non trouvé")
        }
    }
    fun getPlayersSortedByPoints(): List<Player>? {
        val allPlayers = playerRepository.getAllPlayers()
        return allPlayers?.sortedByDescending { it.score }
    }
    fun deleteAllPlayers() {
        playerRepository.deleteAllPlayers()
    }
}