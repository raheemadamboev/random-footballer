package xyz.teamgravity.randomfootballer.data.remote

import retrofit2.http.GET
import xyz.teamgravity.randomfootballer.data.remote.dto.FootballerDto

interface FootballersApi {

    @GET("/random_footballer")
    suspend fun getRandomFootballers(): FootballerDto
}