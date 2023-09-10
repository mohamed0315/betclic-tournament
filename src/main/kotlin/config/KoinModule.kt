package config

import repository.MongoPlayerRepository
import repository.PlayerRepository
import service.RankingService
import org.koin.dsl.module
import org.koin.core.module.Module
import service.PlayerService

val playerRepositoryModule = module {
    single<PlayerRepository> {
        MongoPlayerRepository()
    }
    single {
        PlayerService(get(), get())
    }
    single {
        RankingService(get())
    }

}
