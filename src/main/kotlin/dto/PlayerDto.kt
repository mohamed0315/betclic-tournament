package dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto (
 var pseudo: String,
)

@Serializable
data class UpdatePlayerDto (
 var score: Int,
)

@Serializable
data class PlayerData(
 val pseudo: String,
 val score: Int,
 val rank: Int
)