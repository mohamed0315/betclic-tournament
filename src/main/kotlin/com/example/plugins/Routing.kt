package com.example.plugins

import dto.PlayerDto
import dto.toPlayer
import exception.ErrorResponse
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
    }
}
