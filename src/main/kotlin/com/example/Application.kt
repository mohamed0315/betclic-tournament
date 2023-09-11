package com.example

import com.example.plugins.*
import config.PlayerRepositoryModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    startKoin{
        modules(PlayerRepositoryModule)
    }
    configureSerialization()
    configureRouting()
}
