package com.khunzohn.cleanmvi.feature.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.khunzohn.cleanmvi.R

@EpoxyModelClass(layout = R.layout.model_section_title)
abstract class SectionTitleModel : EpoxyModelWithHolder<SectionTitleModel.Holder>() {

    @EpoxyAttribute
    lateinit var sectionTitle: String

    override fun bind(holder: Holder) {
        holder.tvSectionTitle.text = sectionTitle
    }

    inner class Holder : EpoxyHolder() {
        lateinit var tvSectionTitle: TextView
        override fun bindView(itemView: View) {
            tvSectionTitle = itemView as TextView
        }
    }
}