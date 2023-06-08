package com.houssam.guidomia.data.repository

import com.houssam.guidomia.data.room.CarEntity
import com.houssam.guidomia.domain.pojo.CarModel
import com.houssam.guidomia.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarsRepositoryImp @Inject constructor(private val local: DataSource): CarsRepository {

    override suspend fun insertCars(cars: List<CarEntity>) = local.insertCars(cars)

    override suspend fun getAllCars(): Flow<Resource<List<CarModel>>> = local.getAllCars()
}