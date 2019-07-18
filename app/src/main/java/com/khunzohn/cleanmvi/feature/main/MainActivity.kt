package com.khunzohn.cleanmvi.feature.main

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.khunzohn.cleanmvi.R
import com.khunzohn.cleanmvi.feature.product.ProductListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(
            {
                if (isDestroyed) return@postDelayed
                ProductListActivity.start(this)
            },
            1500
        )
    }
}
