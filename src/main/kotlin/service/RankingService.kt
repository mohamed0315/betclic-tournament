package service

import org.koin.java.KoinJavaComponent.inject
import repository.PlayerRepository

class RankingService() {

    private val playerRepository: PlayerRepository by inject(PlayerRepository::class.java)
    fun getPlayerRank(pseudo: String): Int? {
        val allPlayers = playerRepository.getAllPlayers()
        val sortedPlayers = allPlayers?.sortedByDescending { it.score }

        if (sortedPlayers != null) {
            for ((index, player) in sortedPlayers.withIndex()) {
                if (player.pseudo == pseudo) {
                    return index + 1
                }
            }
        }
        return null
    }
}
