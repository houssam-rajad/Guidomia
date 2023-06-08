package com.houssam.guidomia

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.houssam.guidomia.domain.usecases.GetAllCarsUseCase
import com.houssam.guidomia.data.repository.CarsRepository
import com.houssam.guidomia.domain.pojo.CarModel
import com.houssam.guidomia.ui.viewModels.HomeViewModel
import com.houssam.guidomia.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val getAllCarsUseCase: GetAllCarsUseCase = mockk(relaxed = true)

    private val carRepo : CarsRepository = mockk(relaxed = true)

    private var  viewModel = HomeViewModel(getAllCarsUseCase)



    @Before
    fun init() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Test the filter method`()  {
        val make = "r"
        val model = "b"
        var result: LiveData<List<CarModel>>? = null
        val expected = listOf( CarModel(0,"", listOf(),0.0,"mercedes",0.0,"coupeb", listOf(),3))


        val list = listOf(
            CarModel(0,"", listOf(),0.0,"mercedes",0.0,"coupeb", listOf(),3),
            CarModel(0,"", listOf(),0.0,"bmw",0.0,"3300ib", listOf(),3),
            CarModel(0,"", listOf(),0.0,"toyota",0.0,"toyota", listOf(),3)
        )

        coEvery { carRepo.getAllCars() } returns flow {
            emit(Resource.Success(list
            ))
        }

        coEvery { getAllCarsUseCase } returns GetAllCarsUseCase(carRepo)

        viewModel.viewModelScope.launch {
            result = viewModel.filterListByMakeAndModel(Pair(make, model))
        }


            assertEquals("list",result?.value, expected)
    }
}