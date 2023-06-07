package com.houssam.guidomia.data.local

import com.houssam.guidomia.pojo.CarEntity
import com.houssam.guidomia.pojo.CarModel
import com.houssam.guidomia.util.Resource
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun insertCars(cars: List<CarEntity>)
    suspend fun getAllCars(): Flow<Resource<List<CarModel>>>
}