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