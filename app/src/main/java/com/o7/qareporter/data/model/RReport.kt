package com.o7.qareporter.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RReport(var imageUri: String? = null, var description: String? = null,
                   @PrimaryKey var dateInMillis: Long = System.currentTimeMillis()) : RealmObject()
