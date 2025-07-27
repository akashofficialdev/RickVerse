package com.pegasus.rickverse.di

import com.pegasus.rickverse.data.remote.RickAndMortyApi
import com.pegasus.rickverse.data.repository.CharacterRepositoryImpl
import com.pegasus.rickverse.domain.repository.CharacterRepository
import com.pegasus.rickverse.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRickAndMortyApi(retrofit: Retrofit): RickAndMortyApi =
        retrofit.create(RickAndMortyApi::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickAndMortyApi): CharacterRepository =
        CharacterRepositoryImpl(api)
}
