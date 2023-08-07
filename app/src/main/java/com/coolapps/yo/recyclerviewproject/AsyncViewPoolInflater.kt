package com.coolapps.yo.recyclerviewproject

import android.content.Context
import android.view.View
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import android.view.ViewGroup

/**
 * Class responsible to inflate the required views asynchronously which can be used to create
 * ViewHolders in the RecyclerView Adapter.
 */
class AsyncViewPoolInflater(context: Context) {
    private val inflater: AsyncLayoutInflater = AsyncLayoutInflater(context)
    private val views: MutableList<View> = ArrayList()

    fun inflateViewsInAdvance(parentView: ViewGroup?, numberOfViews: Int) {
        for (i in 0 until numberOfViews) {
            inflater.inflate(
                R.layout.feed_image_item_layout,
                parentView
            ) { view: View, _: Int, _: ViewGroup? -> views.add(view) }
        }
    }

    fun getAndRemoveView(): View? {
        val lastIndex = views.size - 1
        if (lastIndex >= 0) {
            val view = views[lastIndex]
            views.removeAt(lastIndex)
            return view
        }
        return null
    }
}
