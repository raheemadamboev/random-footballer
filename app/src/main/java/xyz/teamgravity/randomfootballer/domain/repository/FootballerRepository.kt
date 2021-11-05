package xyz.teamgravity.randomfootballer.domain.repository

import xyz.teamgravity.randomfootballer.data.remote.dto.FootballerDto

interface FootballerRepository {

    suspend fun getRandomFootballer(): FootballerDto
}