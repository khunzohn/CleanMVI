package com.khunzohn.data.datasource.cache

import com.khunzohn.data.database.dao.MacDao
import com.khunzohn.data.mapper.MacMapper
import com.khunzohn.domain.model.Product
import io.reactivex.Observable

class MacCache constructor(
    private val macDao: MacDao,
    private val macMapper: MacMapper
) : ProductCache<Product.Mac> {
    override fun stream(): Observable<List<Product.Mac>> {
        return macDao.stream().map { macMapper.entityToDomain(it) }
    }

    override fun update(values: List<Product.Mac>) {
        macDao.insert(*macMapper.domainToEntity(values).toTypedArray())
    }

    override fun toggleFavourite(value: Product.Mac) {
        macDao.updateFavourite(value.id, !value.favourite)
    }

    override fun reset() {
        macDao.removeAll()
    }
}