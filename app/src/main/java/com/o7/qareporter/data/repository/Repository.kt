package com.o7.qareporter.data.repository

import com.o7.qareporter.data.mapper.Mapper
import com.o7.qareporter.data.source.DataSource
import io.realm.RealmObject

abstract class Repository<T, D : RealmObject>(private val dataSource: DataSource<D>, private val mapper: Mapper<T, D>) {

    fun save(item: T) {
        dataSource.save(mapper.transform(item))
    }

    fun save(items: List<T>) {
        dataSource.save(items.map { mapper.transform(it) })
    }

    fun getAll(): List<T> = dataSource.getAll()?.map { mapper.transform(it) } ?: listOf()
}