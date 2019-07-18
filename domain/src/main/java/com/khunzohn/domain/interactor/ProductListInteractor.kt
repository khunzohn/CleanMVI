package com.khunzohn.domain.interactor

import com.khunzohn.domain.executor.PostExecutionThread
import com.khunzohn.domain.executor.BackgroundThread
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.repository.ProductRepository
import com.khunzohn.domain.viewstate.product.ProductListPartialState
import io.reactivex.Observable

class ProductListInteractor constructor(
    private val productRepository: ProductRepository,
    mainThread: PostExecutionThread,
    backgroundThread: BackgroundThread
) : BaseInteractor(mainThread, backgroundThread) {

    fun streamMacs(): Observable<ProductListPartialState> {
        return productRepository.streamMacs()
            .map { ProductListPartialState.MacsResult(it) }
            .cast(ProductListPartialState::class.java)
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun streamIPhones(): Observable<ProductListPartialState> {
        return productRepository.streamIPhones()
            .map { ProductListPartialState.IPhonesResult(it) }
            .cast(ProductListPartialState::class.java)
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun fetchMacs(): Observable<ProductListPartialState> {
        return productRepository.fetchMacs()
            .toObservable()
            .map { ProductListPartialState.MacsLoaded }
            .cast(ProductListPartialState::class.java)
            .startWith(ProductListPartialState.LoadingMacs)
            .onErrorReturn { ProductListPartialState.LoadMacsError(it) }
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun fetchIPhones(): Observable<ProductListPartialState> {
        return productRepository.fetchIPhones()
            .toObservable()
            .map { ProductListPartialState.IPhonesLoaded }
            .cast(ProductListPartialState::class.java)
            .startWith(ProductListPartialState.LoadingIphones)
            .onErrorReturn { ProductListPartialState.LoadIPhonesError(it) }
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun toggleFavourite(iPhone: Product.IPhone): Observable<ProductListPartialState> {
        return productRepository.toggleFavourite(iPhone)
            .toObservable()
            .map { ProductListPartialState.FavouriteUpdated }
            .cast(ProductListPartialState::class.java)
            .onErrorReturn { ProductListPartialState.UpdateFavouriteError(it) }
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }

    fun toggleFavourite(mac: Product.Mac): Observable<ProductListPartialState> {
        return productRepository.toggleFavourite(mac)
            .toObservable()
            .map { ProductListPartialState.FavouriteUpdated }
            .cast(ProductListPartialState::class.java)
            .onErrorReturn { ProductListPartialState.UpdateFavouriteError(it) }
            .subscribeOn(backgroundThread.getScheduler())
            .observeOn(mainThread.getScheduler())
    }
}