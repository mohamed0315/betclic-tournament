package com.example

import com.example.plugins.*
import config.playerRepositoryModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    startKoin{
        modules(playerRepositoryModule)
    }
    io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
