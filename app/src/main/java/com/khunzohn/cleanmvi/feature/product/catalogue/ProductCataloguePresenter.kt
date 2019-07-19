package com.khunzohn.cleanmvi.feature.product.catalogue

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.khunzohn.domain.interactor.ProductCatalogureInteractor
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.catalogue.ProductCatalogueViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ProductCataloguePresenter constructor(
    private val interactor: ProductCatalogureInteractor
) : MviBasePresenter<ProductCatalogueView, ProductCatalogueViewState>() {

    private val compositeDisposable = CompositeDisposable()

    override fun bindIntents() {
        val streamMacsState = intent { it.streamMacsIntent() }
            .switchMap { interactor.streamMacs() }

        val streamIPhonesState = intent { it.streamIPhonesIntent() }
            .switchMap { interactor.streamIPhones() }

        val toggleFavouriteState = intent { it.toggleFavouriteIntent() }
            .switchMap {
                if (it is Product.Mac) {
                    interactor.toggleFavourite(it)
                } else {
                    interactor.toggleFavourite(it as Product.IPhone)
                }
            }

        val states = Observable.merge(
            streamMacsState,
            streamIPhonesState,
            toggleFavouriteState
        )

        subscribeViewState(
            states.scan(ProductCatalogueViewState()) { oldState, partialState ->
                partialState.reduce(oldState)
            },
            ProductCatalogueView::render
        )

        intent { it.toggleFavouriteFilterIntent() }
            .subscribe { interactor.toggleFavouriteFilter() }
            .addTo(compositeDisposable)

        intent { it.toggleNewFilterIntent() }
            .subscribe { interactor.toggleNewFilter() }
            .addTo(compositeDisposable)

        intent { it.updateSortOptionIntent() }
            .subscribe { interactor.updateSortOption(it) }
            .addTo(compositeDisposable)
    }

    override fun unbindIntents() {
        compositeDisposable.clear()
    }

}