package com.coolapps.yo.recyclerviewproject

import android.graphics.Canvas
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Item decoration class to draw the sticky header item on the RecyclerView.
 */
class StickyHeaderItemDecoration(
    private val headerOffset: Int, private val sectionHeaderCallback: SectionHeaderCallback
) : ItemDecoration() {
    private var headerView: View? = null
    private var headerText: TextView? = null
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (sectionHeaderCallback.isSection(position)) {
            outRect.top = headerOffset
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (headerView == null) {
            headerView = LayoutInflater.from(parent.context)
                .inflate(R.layout.section_view_item_layout, parent, false)
            headerText = headerView?.findViewById(R.id.section_text)
            fixLayoutSize(headerView, parent)
        }

        var previousHeader: CharSequence = ""
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            val title = sectionHeaderCallback.getSectionHeader(position)
            headerText!!.text = title
            if (previousHeader != title || sectionHeaderCallback.isSection(position)) {
                drawHeader(c, child, headerView)
                previousHeader = title
            }
        }
    }

    private fun drawHeader(c: Canvas, child: View, headerView: View?) {
        c.save()
        c.translate(0f, Math.max(0, child.top - headerView!!.height).toFloat())
        headerView.draw(c)
        c.restore()
    }

    private fun fixLayoutSize(view: View?, parent: ViewGroup) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
        val childWidth = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingStart + parent.paddingEnd,
            view!!.layoutParams.width
        )
        val childHeight = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }
}

/**
 * An interface to get the information related to section data for the given position.
 */
interface SectionHeaderCallback {
    fun isSection(position: Int): Boolean
    fun getSectionHeader(position: Int): CharSequence
}
