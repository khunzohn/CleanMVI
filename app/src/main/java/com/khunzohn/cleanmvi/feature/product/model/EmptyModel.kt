package com.khunzohn.cleanmvi.feature.product.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.khunzohn.cleanmvi.R
import kotlinx.android.synthetic.main.model_empty.view.*

@EpoxyModelClass(layout = R.layout.model_empty)
abstract class EmptyModel : EpoxyModelWithHolder<EmptyModel.Holder>() {

    @EpoxyAttribute
    lateinit var emptyMessage: String

    override fun bind(holder: Holder) {
        holder.tvEmpty.text = emptyMessage
    }

    inner class Holder : EpoxyHolder() {
        lateinit var tvEmpty: TextView
        override fun bindView(itemView: View) {
            tvEmpty = itemView.tvEmpty
        }
    }
}