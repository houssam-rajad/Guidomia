package com.houssam.guidomia.domain.pojo

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
    val rating: Int,
    var isExpanded: Boolean = false
)