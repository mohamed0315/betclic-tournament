package com.example.plugins

import dto.PlayerDto
import dto.UpdatePlayerDto
import dto.toPlayer
import exception.DatabaseUpdateException
import exception.ErrorResponse
import exception.PlayerNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import service.PlayerService

fun Application.configureRouting() {

    val service = PlayerService()

    routing {
        route("/player"){
            post {
                val request = call.receive<PlayerDto>()
                val player = request.toPlayer()
                service.createPlayer(player)?.let{
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
                    service.updatePlayerScore(pseudo, playerUpdateDto.score)
                    call.respondText("Score du joueur mis à jour avec succès")
                } catch (e: PlayerNotFoundException) {
                    call.respond(HttpStatusCode.NotFound, e.message ?: "Joueur non trouvé")
                } catch (e: DatabaseUpdateException) {
                    call.respond(HttpStatusCode.InternalServerError, e.message ?: "Erreur de mise à jour de la base de données")
                }
            }
        }
    }
}
