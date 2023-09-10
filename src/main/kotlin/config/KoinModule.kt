package config

import repository.MongoPlayerRepository
import repository.PlayerRepository
import service.RankingService
import org.koin.dsl.module
import org.koin.core.module.Module

val playerRepositoryModule = module {
    single<PlayerRepository> {
        MongoPlayerRepository()
    }
}
