package repository

import com.mongodb.client.result.UpdateResult
import exception.DatabaseUpdateException
import exception.PlayerNotFoundException
import model.Player
import org.litote.kmongo.*

interface PlayerRepository {
    fun createPlayer(player: Player): Id<Player>?
    fun updatePlayerScore(pseudo: String, score: Int): UpdateResult?
}



