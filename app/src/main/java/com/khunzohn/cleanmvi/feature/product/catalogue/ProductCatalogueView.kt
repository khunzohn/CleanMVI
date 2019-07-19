package com.khunzohn.cleanmvi.feature.product.catalogue

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.catalogue.ProductCatalogueViewState
import com.khunzohn.domain.viewstate.product.catalogue.SortOption
import io.reactivex.Observable

interface ProductCatalogueView : MvpView {

    fun streamMacsIntent() : Observable<Any>

    fun streamIPhonesIntent() : Observable<Any>

    fun toggleNewFilterIntent() : Observable<Any>

    fun toggleFavouriteFilterIntent() : Observable<Any>

    fun updateSortOptionIntent() : Observable<SortOption>

    fun toggleFavouriteIntent() : Observable<Product>

    fun render(viewState: ProductCatalogueViewState)
}