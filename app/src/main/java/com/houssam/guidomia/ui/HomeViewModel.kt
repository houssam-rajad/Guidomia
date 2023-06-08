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
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCarsUseCase: GetAllCarsUseCase): ViewModel() {

    private val _allCarsList = MutableLiveData<Resource<List<CarModel>>>()
    val allCarsList: LiveData<Resource<List<CarModel>>> = _allCarsList

    private val _filterList= MutableLiveData<List<CarModel>>()
    val filterList: LiveData<List<CarModel>> = _filterList


    fun getAllCars() {
        viewModelScope.launch {
            getAllCarsUseCase().collectLatest { response ->
                _allCarsList.postValue(response)
            }
        }
    }


    fun filterListByMakeAndModel(pair:Pair<String, String>): LiveData<List<CarModel>> {
        if (pair.first.isNotEmpty()) {
            _filterList.postValue(if (pair.second.isNotEmpty()) {
                _allCarsList.value?.data?.filter {
                    it.make.lowercase(Locale.ROOT).contains(pair.first) && it.model.lowercase(
                        Locale.ROOT
                    ).contains(pair.second)
                }
            } else {
                _allCarsList.value?.data?.filter {
                    it.make.lowercase(Locale.ROOT).contains(pair.first)
                }
            })

        } else {
            _filterList.postValue(if (pair.second.isNotEmpty()) {
                _allCarsList.value?.data?.filter {
                    it.model.lowercase(Locale.ROOT).contains(pair.second)
                }
            } else {
                _allCarsList.value?.data
            })
        }

        return _filterList
    }
}