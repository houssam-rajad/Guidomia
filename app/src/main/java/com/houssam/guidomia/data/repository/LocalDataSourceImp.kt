package com.houssam.guidomia.data.repository

import com.houssam.guidomia.data.room.CarEntity
import com.houssam.guidomia.domain.pojo.CarModel
import com.houssam.guidomia.data.room.CarsDao
import com.houssam.guidomia.util.Resource
import com.houssam.guidomia.util.toCarModelList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val carsDao: CarsDao) : DataSource {

    override suspend fun insertCars(cars: List<CarEntity>) = carsDao.insertCars(cars)

    override suspend fun getAllCars(): Flow<Resource<List<CarModel>>> {

        return flow {
            emit(Resource.Loading())
            val allCars = carsDao.getAllCars().map { it.toCarModelList() }
            allCars.collect {
                emit(Resource.Success(it))
            }
        }

    }
}