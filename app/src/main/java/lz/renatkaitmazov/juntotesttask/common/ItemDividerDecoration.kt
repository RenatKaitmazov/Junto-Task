package lz.renatkaitmazov.juntotesttask.common

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * A divider for a recycler view.
 *
 * @author Renat Kaitmazov
 */
class ItemDividerDecoration(private val divider: Drawable,
                            private val startPadding: Int = 0,
                            private val endPadding: Int = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // Do not make any offset for the very first item in the recycler view.
        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }
        // Otherwise the top offset is equal to the height of the divider drawable.
        outRect.top = divider.intrinsicHeight
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerStartPosition = parent.paddingStart + startPadding
        val dividerEndPosition = parent.width - parent.paddingEnd - endPadding
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childLayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + childLayoutParams.bottomMargin
            val dividerBottom = dividerTop + divider.intrinsicHeight
            divider.setBounds(dividerStartPosition, dividerTop, dividerEndPosition, dividerBottom)
            divider.draw(canvas)
        }
    }

}