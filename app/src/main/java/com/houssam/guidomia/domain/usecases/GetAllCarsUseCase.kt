package com.houssam.guidomia.domain.usecases

import com.houssam.guidomia.data.repository.CarsRepository
import com.houssam.guidomia.domain.pojo.CarModel
import com.houssam.guidomia.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCarsUseCase @Inject constructor(
    private val carRepository: CarsRepository,
) {

    suspend operator fun invoke(): Flow<Resource<List<CarModel>>> = carRepository.getAllCars()
}