package com.houssam.guidomia.di

import android.app.Application
import androidx.room.Room
import com.houssam.guidomia.data.domain.usecases.GetAllCarsUseCase
import com.houssam.guidomia.data.local.CarsRepository
import com.houssam.guidomia.data.local.CarsRepositoryImp
import com.houssam.guidomia.data.local.DataSource
import com.houssam.guidomia.data.local.LocalDataSourceImp
import com.houssam.guidomia.pojo.CarsDao
import com.houssam.guidomia.pojo.CarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CarsModule {

    @Singleton
    @Provides
    fun provideCarDatabase(
        application: Application,
        roomCallback: CarsDatabase.PrepopulateCallback,
    ): CarsDatabase =
        Room.databaseBuilder(
            application,
            CarsDatabase::class.java,
            "cars_database"
        )
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build()

    @Singleton
    @Provides
    fun provideCarsDao(database: CarsDatabase) = database.carsDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(carsDao: CarsDao): DataSource = LocalDataSourceImp(carsDao)

    @Singleton
    @Provides
    fun provideRepository(dataSource: DataSource): CarsRepository = CarsRepositoryImp(dataSource)


    @Singleton
    @Provides
    fun provideGetAllCarsUseCase(carsRepository: CarsRepository) = GetAllCarsUseCase(carsRepository)
}