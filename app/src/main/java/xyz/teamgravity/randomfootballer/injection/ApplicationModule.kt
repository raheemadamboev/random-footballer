package xyz.teamgravity.randomfootballer.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.teamgravity.randomfootballer.data.remote.FootballersApi
import xyz.teamgravity.randomfootballer.data.repository.FootballerRepositoryImpl
import xyz.teamgravity.randomfootballer.domain.repository.FootballerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideFootballersApi(): FootballersApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://192.168.1.9:8080")
        .build()
        .create(FootballersApi::class.java)

    @Provides
    fun provideFootballerRepository(api: FootballersApi): FootballerRepository = FootballerRepositoryImpl(api)
}