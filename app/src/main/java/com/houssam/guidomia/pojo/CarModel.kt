package com.houssam.guidomia.pojo

import kotlinx.serialization.Serializable

@Serializable

data class CarModel(
    val id: Int=0,
    val image:String,
    val consList: List<String>,
    val customerPrice: Double,
    val make: String,
    val marketPrice: Double,
    val model: String,
    val prosList: List<String>,
    val rating: Int
)