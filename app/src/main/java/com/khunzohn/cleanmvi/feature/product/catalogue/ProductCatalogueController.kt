package com.khunzohn.cleanmvi.feature.product.catalogue

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.khunzohn.cleanmvi.R
import com.khunzohn.cleanmvi.feature.product.model.ProductModel_
import com.khunzohn.cleanmvi.feature.product.model.empty
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.catalogue.ProductCatalogueViewState
import io.reactivex.subjects.PublishSubject

class ProductCatalogueController constructor(
    private val context: Context
) : TypedEpoxyController<ProductCatalogueViewState>() {

    private val updateFavouriteSubject = PublishSubject.create<Product>()

    fun updateFavouriteClicks() = updateFavouriteSubject

    override fun buildModels(data: ProductCatalogueViewState) {

        if (data.products.isEmpty()) {
            empty {
                id("Empty")
                emptyMessage(context.getString(R.string.empty_product_label))
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
            }
        } else {
            data.products.forEach {
                ProductModel_()
                    .id(if (it is Product.Mac) it.id else (it as Product.IPhone).id)
                    .updateFavouriteAction { product ->
                        updateFavouriteSubject.onNext(product)
                    }
                    .spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }
                    .appleProduct(it)
                    .addTo(this)
            }
        }
    }
}