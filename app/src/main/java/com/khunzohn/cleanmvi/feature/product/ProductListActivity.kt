package com.khunzohn.cleanmvi.feature.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.khunzohn.cleanmvi.R
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.ProductListViewState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_product_list.*
import org.koin.android.ext.android.inject

class ProductListActivity : MviActivity<ProductListView, ProductListPresenter>(), ProductListView {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val streamMacsSubject = PublishSubject.create<Any>()
    private val streamIPhonesSubject = PublishSubject.create<Any>()
    private val fetchMacsSubject = PublishSubject.create<Any>()
    private val fetchIPhonesSubject = PublishSubject.create<Any>()

    private val presenter: ProductListPresenter by inject()

    private lateinit var controller: ProductListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        controller = ProductListController(this)
        rvContent.setController(controller)
        rvContent.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        streamMacsSubject.onNext(Any())
        streamIPhonesSubject.onNext(Any())
        fetchMacsSubject.onNext(Any())
        fetchIPhonesSubject.onNext(Any())
    }

    override fun createPresenter(): ProductListPresenter = presenter

    override fun streamMacsIntent(): Observable<Any> {
        return streamMacsSubject.take(1) // only need one time to stream
    }

    override fun streamIPhonesIntent(): Observable<Any> {
        return streamIPhonesSubject.take(1) // only need one time to stream
    }

    override fun fetchMacsIntent(): Observable<Any> {
        return fetchMacsSubject.take(1)
    }

    override fun fetchIPhonesIntent(): Observable<Any> {
        return fetchIPhonesSubject.take(1)
    }

    override fun toggleFavouriteIntent(): Observable<Product> {
        return controller.updateFavouriteClicks()
    }

    override fun retryFetchMacsIntent(): Observable<Any> {
        return controller.retryFetchMacsClicks()
    }

    override fun retryFetchIPhonesIntent(): Observable<Any> {
        return controller.retryFetchIPhonesClicks()
    }

    override fun render(viewState: ProductListViewState) {
        controller.setData(viewState)
    }
}