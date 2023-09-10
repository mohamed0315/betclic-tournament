package repository

import com.mongodb.client.result.UpdateResult
import exception.DatabaseUpdateException
import exception.PlayerNotFoundException
import model.Player
import org.litote.kmongo.*

class MongoPlayerRepository : PlayerRepository {
    private val client = KMongo.createClient("mongodb+srv://mabouyaaqoub:mabouyaaqoub@cluster99.cpis9kb.mongodb.net/?retryWrites=true&w=majority")
    private val database = client.getDatabase("players")
    private val collection = database.getCollection<Player>()

    override fun createPlayer(player: Player): Id<Player>? {
        collection.insertOne(player)
        return player.id;
    }
    override fun updatePlayerScore(pseudo: String, score: Int): UpdateResult? {
        val player = collection.findOne(Player::pseudo eq pseudo)
        return if (player != null) {
            try {
                collection.updateOne(Player::pseudo eq pseudo, setValue(Player::score, score))
            } catch (e: Exception) {
                throw DatabaseUpdateException("Erreur lors de la mise à jour de la base de données")
            }
        } else {
            throw PlayerNotFoundException("Joueur avec le pseudo $pseudo non trouvé")
        }
    }
     override fun getAllPlayers(): List<Player> =
        collection.find()
            .toList()

    override fun getPlayerByPseudo(pseudo: String): Player? =
        collection.findOne(Player::pseudo eq pseudo)

    override fun deleteAllPlayers() {
        try {
            collection.deleteMany(Player::pseudo exists true)
        } catch (e: Exception) {
            throw DatabaseUpdateException("Erreur lors de la suppression de la base de données")
        }
    }
}