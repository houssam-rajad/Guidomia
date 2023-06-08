package com.houssam.guidomia.data.repository

import com.houssam.guidomia.data.room.CarEntity
import com.houssam.guidomia.domain.pojo.CarModel
import com.houssam.guidomia.util.Resource
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun insertCars(cars: List<CarEntity>)
    suspend fun getAllCars(): Flow<Resource<List<CarModel>>>
}