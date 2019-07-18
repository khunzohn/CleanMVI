package com.khunzohn.cleanmvi.feature.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.khunzohn.cleanmvi.R

@EpoxyModelClass(layout = R.layout.model_horizontal_loading)
abstract class HorizontalLoadingModel : EpoxyModelWithHolder<HorizontalLoadingModel.Holder>() {

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            // nothing to do
        }
    }
}
