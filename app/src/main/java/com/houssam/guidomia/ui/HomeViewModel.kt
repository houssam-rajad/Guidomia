package com.houssam.guidomia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houssam.guidomia.data.domain.usecases.GetAllCarsUseCase
import com.houssam.guidomia.pojo.CarModel
import com.houssam.guidomia.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCarsUseCase: GetAllCarsUseCase): ViewModel() {

    private val _allCarsList = MutableLiveData<Resource<List<CarModel>>>()
    val allCarsList: LiveData<Resource<List<CarModel>>> = _allCarsList



    fun getAllCars()
    {
        viewModelScope.launch {
            getAllCarsUseCase().collectLatest {
                response-> _allCarsList.postValue(response)
            }
        }
    }
}