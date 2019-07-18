package com.khunzohn.cleanmvi.feature.product

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.khunzohn.domain.interactor.ProductListInteractor
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.ProductListViewState
import io.reactivex.Observable

class ProductListPresenter constructor(
    private val interactor: ProductListInteractor
) : MviBasePresenter<ProductListView, ProductListViewState>() {

    override fun bindIntents() {

        val streamMacsState = intent { it.streamMacsIntent() }
            .switchMap { interactor.streamMacs() }

        val streamIPhonesState = intent { it.streamIPhonesIntent() }
            .switchMap { interactor.streamIPhones() }

        val fetchMacsState = intent { it.fetchMacsIntent() }
            .switchMap { interactor.fetchMacs() }


        val retryFetchMacsState = intent { it.retryFetchMacsIntent() }
            .switchMap { interactor.fetchMacs() }

        val fetchIPhonesState = intent { it.fetchIPhonesIntent() }
            .switchMap { interactor.fetchIPhones() }

        val retryFetchIPhonesState = intent { it.retryFetchIPhonesIntent() }
            .switchMap { interactor.fetchIPhones() }

        val toggleFavouriteState = intent { it.toggleFavouriteIntent() }
            .switchMap {
                if (it is Product.Mac) {
                    interactor.toggleFavourite(it)
                } else {
                    interactor.toggleFavourite(it as Product.IPhone)
                }
            }

        val states = Observable.mergeArray(
            streamMacsState,
            streamIPhonesState,
            fetchMacsState,
            retryFetchMacsState,
            fetchIPhonesState,
            retryFetchIPhonesState,
            toggleFavouriteState
        )

        subscribeViewState(
            states
                .scan(ProductListViewState()) { oldState, partialState ->
                    partialState.reduce(oldState)
                }
                .distinctUntilChanged(),
            ProductListView::render
        )
    }
}