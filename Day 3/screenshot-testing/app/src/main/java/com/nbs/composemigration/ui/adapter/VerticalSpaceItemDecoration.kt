package com.nbs.composemigration.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        outRect.bottom = verticalSpaceHeight

        // Add top space to the first item for consistent spacing if needed,
        // but typically the RecyclerView itself or its container handles top padding.
        if (position == 0) {
            outRect.top = verticalSpaceHeight
        }
    }
}