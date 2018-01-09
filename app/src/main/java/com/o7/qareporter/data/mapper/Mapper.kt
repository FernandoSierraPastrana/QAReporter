package com.o7.qareporter.data.mapper

import io.realm.RealmObject

interface Mapper<I, O : RealmObject> {

    fun transform(input: I): O

    fun transform(input: O): I
}