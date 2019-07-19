package com.khunzohn.cleanmvi.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CatalogueItemDecoration(
    private val spacingVertical: Int,
    private val spacingHorizontal: Int
) : RecyclerView.ItemDecoration() {

    private val spanCount = 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        val leftColumn = (position % spanCount) == 0
        if (leftColumn) {
            outRect.right = 0
            outRect.left = 0
        } else {
            outRect.right = 0
            outRect.left = spacingHorizontal
        }
        outRect.bottom = spacingVertical
    }
}
