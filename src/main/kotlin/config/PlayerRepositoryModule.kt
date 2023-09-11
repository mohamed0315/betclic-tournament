package config

import repository.MongoPlayerRepository
import repository.PlayerRepository
import service.RankingService
import org.koin.dsl.module
import service.PlayerService

val PlayerRepositoryModule = module {
    single<PlayerRepository> {
        MongoPlayerRepository()
    }
    single<PlayerService> {
        PlayerService()
    }
    single<RankingService> {
        RankingService()
    }

}
