package com.khunzohn.cleanmvi.feature.product.catalogue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import com.khunzohn.cleanmvi.R
import com.khunzohn.cleanmvi.util.CatalogueItemDecoration
import com.khunzohn.data.util.dpToPx
import com.khunzohn.domain.model.Product
import com.khunzohn.domain.viewstate.product.catalogue.ProductCatalogueViewState
import com.khunzohn.domain.viewstate.product.catalogue.SortOption
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_product_catalogue.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class ProductCatalogueActivity :
        MviActivity<ProductCatalogueView, ProductCataloguePresenter>(),
        ProductCatalogueView {

    companion object {
        const val TYPE_MAC = 1
        const val TYPE_I_PHONE = 2

        fun start(context: Context, productType: Int) {
            val intent = Intent(context, ProductCatalogueActivity::class.java)
            intent.putExtra("productType", productType)
            context.startActivity(intent)
        }
    }

    private val streamMacsSubject = PublishSubject.create<Any>()

    private val streamIPhonesSubject = PublishSubject.create<Any>()

    private val updateSortOptionSubject = PublishSubject.create<SortOption>()

    private var productType: Int = -1

    private val presenter: ProductCataloguePresenter by inject()

    private lateinit var catalogueController: ProductCatalogueController

    private lateinit var sortItems: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_catalogue)

        sortItems = resources.getStringArray(R.array.product_sort)

        productType = intent.extras?.getInt("productType", -1) ?: -1

        if (productType != TYPE_MAC && productType != TYPE_I_PHONE) {
            Timber.d("Unknown product type")
            finish()
        }

        toolbar.title = if (productType == TYPE_MAC) getString(R.string.mac_section_title)
        else getString(R.string.iphone_section_title)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvSortOption.setOnClickListener { showSortOptionDialog() }

        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        if (productType == TYPE_MAC) {
            streamMacsSubject.onNext(Any())
        } else {
            streamIPhonesSubject.onNext(Any())
        }
    }

    override fun createPresenter(): ProductCataloguePresenter = presenter

    override fun streamMacsIntent(): Observable<Any> = streamMacsSubject.take(1)

    override fun streamIPhonesIntent(): Observable<Any> = streamIPhonesSubject.take(1)

    override fun toggleNewFilterIntent(): Observable<Any> = RxView.clicks(ivNew)

    override fun toggleFavouriteFilterIntent(): Observable<Any> = RxView.clicks(ivFavourite)

    override fun updateSortOptionIntent(): Observable<SortOption> {
        return updateSortOptionSubject
    }

    override fun toggleFavouriteIntent(): Observable<Product> {
        return catalogueController.updateFavouriteClicks()
    }

    override fun render(viewState: ProductCatalogueViewState) {

        viewState.favouriteError?.let {
            it.localizedMessage?.run {
                Toast.makeText(this@ProductCatalogueActivity,
                        this, Toast.LENGTH_SHORT).show()
            }
        }

        ivFavourite.setImageResource(
                if (viewState.favouriteFilterOn) R.drawable.ic_favorite_active_36dp
                else R.drawable.ic_favorite_inactive_36dp
        )

        ivNew.setImageResource(
                if (viewState.newFilterOn) R.drawable.ic_new_active_36dp
                else R.drawable.ic_new_inactive_36dp
        )

        tvSortOption.text = when (viewState.sortOption) {
            SortOption.LOW_TO_HIGH -> getString(R.string.product_sort_price_low_to_high)
            SortOption.HIGH_TO_LOW -> getString(R.string.product_sort_price_high_to_low)
        }

        catalogueController.setData(viewState)
    }

    private fun setUpRecyclerView() {
        catalogueController = ProductCatalogueController(this)
        rvContent.setController(catalogueController)

        val spanCount = 2
        val layoutManager = GridLayoutManager(
                this, spanCount,
                RecyclerView.VERTICAL, false
        )
        catalogueController.spanCount = spanCount

        layoutManager.spanSizeLookup = catalogueController.spanSizeLookup

        rvContent.addItemDecoration(
                CatalogueItemDecoration(
                        spacingHorizontal = dpToPx(4f),
                        spacingVertical = dpToPx(16f)
                )
        )
        rvContent.layoutManager = layoutManager
    }

    private fun showSortOptionDialog() {
        if (isDestroyed) return

        AlertDialog.Builder(this)
                .setSingleChoiceItems(sortItems, -1) { dialog, which ->
                    dialog.dismiss()
                    updateSortOptionSubject.onNext(toSortOption(sortItems[which]))
                }
                .create()
                .show()
    }

    private fun toSortOption(sortOption: String): SortOption {
        return when (sortOption) {
            getString(R.string.product_sort_price_low_to_high) -> SortOption.LOW_TO_HIGH
            else -> SortOption.HIGH_TO_LOW
        }
    }
}