package com.houssam.guidomia.data.domain.usecases

import com.houssam.guidomia.data.local.CarsRepository
import com.houssam.guidomia.pojo.CarModel
import com.houssam.guidomia.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCarsUseCase @Inject constructor(
    private val carRepository: CarsRepository,
) {

    suspend operator fun invoke(): Flow<Resource<List<CarModel>>> = carRepository.getAllCars()
}