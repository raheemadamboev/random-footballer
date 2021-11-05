package xyz.teamgravity.randomfootballer.data.repository

import xyz.teamgravity.randomfootballer.data.remote.FootballersApi
import xyz.teamgravity.randomfootballer.data.remote.dto.FootballerDto
import xyz.teamgravity.randomfootballer.domain.repository.FootballerRepository

class FootballerRepositoryImpl(private val api: FootballersApi) : FootballerRepository {

    override suspend fun getRandomFootballer(): FootballerDto {
        return api.getRandomFootballers()
    }
}