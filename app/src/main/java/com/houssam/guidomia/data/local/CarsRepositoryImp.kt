package com.houssam.guidomia.data.local

import com.houssam.guidomia.annotations.LocalDataSource
import com.houssam.guidomia.pojo.CarEntity
import com.houssam.guidomia.pojo.CarModel
import com.houssam.guidomia.util.Resource
import kotlinx.coroutines.flow.Flow

class CarsRepositoryImp(@LocalDataSource val local: DataSource): CarsRepository {

    override suspend fun insertCars(cars: List<CarEntity>) = local.insertCars(cars)

    override suspend fun getAllCars(): Flow<Resource<List<CarModel>>> = local.getAllCars()
}