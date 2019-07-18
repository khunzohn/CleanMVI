package com.khunzohn.domain.viewstate.product

import com.khunzohn.domain.model.Product

sealed class ProductListPartialState {

    abstract fun reduce(oldState: ProductListViewState): ProductListViewState

    data class MacsResult(val macs: List<Product.Mac>) : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                macs = macs
            )
        }
    }

    object MacsLoaded : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                loadMacsError = null,
                loadingMacs = false
            )
        }
    }

    object LoadingMacs : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                loadingMacs = true,
                loadMacsError = null
            )
        }
    }

    data class LoadMacsError(val error: Throwable) : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                loadingMacs = false,
                loadMacsError = error
            )
        }
    }

    data class IPhonesResult(val iPhones: List<Product.IPhone>) : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                iPhones = iPhones
            )
        }
    }

    object LoadingIphones : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                loadingIPhones = true,
                loadIPhonesError = null
            )
        }
    }

    object IPhonesLoaded : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                loadIPhonesError = null,
                loadingIPhones = false
            )
        }
    }

    data class LoadIPhonesError(val error: Throwable) : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                loadingIPhones = false,
                loadIPhonesError = error
            )
        }
    }

    object FavouriteUpdated : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                favouriteError = null // re setting error just in case there's any
            )
        }
    }

    data class UpdateFavouriteError(
        val error: Throwable? // Yes, nullable for one time event, toast for example.
    ) : ProductListPartialState() {
        override fun reduce(oldState: ProductListViewState): ProductListViewState {
            return oldState.copy(
                favouriteError = error
            )
        }
    }
}