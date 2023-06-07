package com.houssam.guidomia.annotations

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalDataSource(
    /** The name.  */
    val value: String = "localDataSource"
)
