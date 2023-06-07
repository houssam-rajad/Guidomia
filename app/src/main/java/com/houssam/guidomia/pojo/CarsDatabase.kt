package com.houssam.guidomia.pojo

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.houssam.guidomia.R
import com.houssam.guidomia.data.local.CarConverter
import com.houssam.guidomia.util.toEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [CarEntity::class], version = 1)
@TypeConverters(CarConverter::class)
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao



    class PrepopulateCallback @Inject constructor(
        @ApplicationContext private val context: Context,
        private val carDao: Provider<CarsDao>) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(SupervisorJob()).launch {
                val rawJsonString = context.resources.openRawResource(R.raw.car_list)
                    .bufferedReader().use { it.readText() }
                val carsList = Json.decodeFromString<List<CarModel>>(rawJsonString)
                carDao.get().insertCars(carsList.map { it.toEntity() })
            }
        }
    }
}