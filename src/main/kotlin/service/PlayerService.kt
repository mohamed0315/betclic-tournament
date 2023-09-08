package service

import model.Player
import org.litote.kmongo.Id
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class PlayerService {
    private val client = KMongo.createClient("mongodb+srv://mabouyaaqoub:mabouyaaqoub@cluster99.cpis9kb.mongodb.net/?retryWrites=true&w=majority")
    private val database = client.getDatabase("players")
    private val collection = database.getCollection<Player>()

    fun createPlayer(player: Player): Id<Player>? {
        collection.insertOne(player)
        return player.id;
    }
}

