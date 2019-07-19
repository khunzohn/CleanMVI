package com.khunzohn.cleanmvi.feature.product.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.khunzohn.cleanmvi.R
import com.khunzohn.cleanmvi.feature.product.catalogue.ProductCatalogueActivity
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.list.ProductListViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_product_list.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class ProductListActivity : MviActivity<ProductListView, ProductListPresenter>(), ProductListView {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val compositeDisposable = CompositeDisposable()

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

        controller.seeAllClicks()
            // prevent lunching multiple activity just in case a monkey plays with `see all` button
            .debounce(300,TimeUnit.MILLISECONDS)
            .subscribe { ProductCatalogueActivity.start(this, it) }
            .addTo(compositeDisposable)
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

        viewState.favouriteError?.let {
            it.localizedMessage?.run {
                Toast.makeText(this@ProductListActivity, this, Toast.LENGTH_SHORT).show()
            }
        }

        controller.setData(viewState)
    }
}