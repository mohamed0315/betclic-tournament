package com.example.plugins

import dto.PlayerDto
import dto.UpdatePlayerDto
import dto.toDto
import dto.toPlayer
import exception.DatabaseUpdateException
import exception.ErrorResponse
import exception.PlayerNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.Player
import org.koin.java.KoinJavaComponent.inject
import repository.PlayerRepository
import service.PlayerService

fun Application.configureRouting() {
    val playerRepository :  PlayerRepository by inject(PlayerRepository::class.java)
    val playerService : PlayerService by inject(PlayerService::class.java)

    routing {

        route("/player"){
            post {
                val request = call.receive<PlayerDto>()
                val player = request.toPlayer()
                playerRepository.createPlayer(player)?.let{
                    userid -> call.response.headers.append("Header-Id-Player", userid.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST)
            }
        }
        route("/player/{pseudo}"){
            patch {
                val pseudo = call.parameters["pseudo"] ?: return@patch call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST)
                val playerUpdateDto = call.receive<UpdatePlayerDto>()
                try {
                    playerRepository.updatePlayerScore(pseudo, playerUpdateDto.score)
                    call.respondText("Score du joueur mis à jour avec succès")
                } catch (e: PlayerNotFoundException) {
                    call.respond(HttpStatusCode.NotFound, e.message ?: "Joueur non trouvé")
                } catch (e: DatabaseUpdateException) {
                    call.respond(HttpStatusCode.InternalServerError, e.message ?: "Erreur de mise à jour de la base de données")
                }
            }
        }
        route("/players"){
            get {
                val players = playerRepository.getAllPlayers()?.map(Player::toDto)
                if (players != null) {
                    call.respond(players)
                } else {
                    call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND)
                }
            }
        }
        route("/player/{pseudo}"){
            get {
                val pseudo = call.parameters["pseudo"] ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST)
                try {
                    val player = playerService.getPlayerData(pseudo)
                    if (player != null) {
                        call.respond(player)
                    } else {
                        call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND)
                    }
                } catch (e: PlayerNotFoundException) {
                    call.respond(HttpStatusCode.NotFound, e.message ?: "Joueur non trouvé")
                }
            }
        }
        route("/players/sorted"){
            get {
                val players = playerService.getPlayersSortedByPoints()?.map(Player::toDto)
                if (players != null) {
                    call.respond(players)
                } else {
                    call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND)
                }
            }
        }
        route("/players"){
            delete {
                try {
                    playerService.deleteAllPlayers()
                    call.respondText("Joueurs supprimés avec succès")
                } catch (e: DatabaseUpdateException) {
                    call.respond(HttpStatusCode.InternalServerError, e.message ?: "Erreur de mise à jour de la base de données")
                }
            }
        }
    }
}
