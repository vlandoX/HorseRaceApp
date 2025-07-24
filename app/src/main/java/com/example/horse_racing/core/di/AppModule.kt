package com.example.horse_racing.core.di

import android.app.Application
import androidx.room.Room
import com.example.horse_racing.race.domain.use_case.InsertRaceUseCase
import com.example.horse_racing.core.data.local.RaceDao
import com.example.horse_racing.core.data.local.RaceDb
import com.example.horse_racing.core.data.repository.RaceRepositoryImpl
import com.example.horse_racing.core.domain.repository.RaceRepository
import com.example.horse_racing.history_races.domain.use_case.DeleteRaceUseCase
import com.example.horse_racing.history_races.domain.use_case.GetAllRacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRaceDao(database: RaceDb): RaceDao = database.raceDao

    @Singleton
    @Provides
    fun provideNoteDb(application: Application): RaceDb {
        return Room.databaseBuilder(
            context = application,
            klass = RaceDb::class.java,
            name = "horse_racing_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRaceRepository(
        raceDao: RaceDao
    ): RaceRepository {
        return RaceRepositoryImpl(raceDao)
    }

    @Provides
    @Singleton
    fun provideGetAllRacesUseCase(
        raceRepository: RaceRepository
    ): GetAllRacesUseCase {
        return GetAllRacesUseCase(raceRepository)
    }


    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(
        raceRepository: RaceRepository
    ): DeleteRaceUseCase {
        return DeleteRaceUseCase(raceRepository)
    }


    @Provides
    @Singleton
    fun provideInsertRaceUseCase(
        raceRepository: RaceRepository
    ): InsertRaceUseCase {
        return InsertRaceUseCase(raceRepository)
    }

}

