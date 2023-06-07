package com.houssam.guidomia.util

import com.houssam.guidomia.pojo.CarEntity
import com.houssam.guidomia.pojo.CarModel


fun CarModel.toEntity(): CarEntity
{
    return CarEntity(
        id,
        image,
        consList,
        customerPrice,
        make,
        marketPrice,
        model,
        prosList,
        rating,
    )

}

fun CarEntity.toCarModel(): CarModel =
    CarModel(
        id,
        image,
        consList,
        customerPrice,
        make,
        marketPrice,
        model,
        prosList,
        rating,
    )

fun List<CarEntity>.toCarModelList(): List<CarModel> =
    map { it.toCarModel() }

fun Double.toFormattedPricing(): String = this.div(1000).toInt().toString()+ "K"