package com.khunzohn.cleanmvi.feature.product.list

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.list.ProductListViewState
import io.reactivex.Observable

interface ProductListView : MvpView{

    fun streamMacsIntent(): Observable<Any>

    fun streamIPhonesIntent(): Observable<Any>

    fun fetchMacsIntent(): Observable<Any>

    fun retryFetchMacsIntent() : Observable<Any>

    fun fetchIPhonesIntent(): Observable<Any>

    fun retryFetchIPhonesIntent() : Observable<Any>

    fun toggleFavouriteIntent(): Observable<Product>

    fun render(viewState: ProductListViewState)
}