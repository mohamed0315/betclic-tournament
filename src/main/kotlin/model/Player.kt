package model

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Player (
    @BsonId
    val id: Id<Player>? = null,
    var pseudo: String,
    var score: Int,
)